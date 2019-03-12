<style type="text/css">
.blog_table_footer {
  display: flex;
  box-sizing: content-box;
  padding-top: 10px;
  padding-bottom: 0px;
  margin-bottom: 0px;
  justify-content: space-between;
}
</style>
<template>
  <div>
    <div style="display: flex;justify-content: flex-start">
      <el-input placeholder="通过标题搜索该分类下的博客..." prefix-icon="el-icon-search" v-model="keywords" style="width: 400px" size="mini">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="searchClick">搜索
      </el-button>
      <el-button type="primary" size="mini" @click="writeArticle">写文章
      </el-button>
    </div>
    <el-table ref="multipleTable" :data="articles" tooltip-effect="dark" style="width: 100%;overflow-x: hidden; overflow-y: hidden;" max-height="390" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column type="selection" align="left">
      </el-table-column>
      <el-table-column label="标题" align="left">
        <template slot-scope="scope">
          <span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.articleTitle}}</span>
        </template>
      </el-table-column>
      <el-table-column label="最近编辑时间" align="left">
        <template slot-scope="scope">{{ scope.row.updated | formatDateTime}}</template>
      </el-table-column>
      <el-table-column prop="cateName" label="所属分类" align="left">
      </el-table-column>
      <el-table-column prop="type" label="文章类型" align="left">
        <template slot-scope="scope">{{ scope.row.type | formatType}}</template>
      </el-table-column>
      <el-table-column label="操作" align="left">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="blog_table_footer">
      <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.articles.length>0" :disabled="this.selItems.length==0" @click="deleteMany">批量删除
      </el-button>
      <span></span>
      <el-pagination background :page-size="pageSize" layout="prev, pager, next" :total="totalCount" @current-change="currentChange" v-show="this.articles.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {putRequest, postRequest} from '../../../utils/api'
  import {getRequest} from '../../../utils/api'
//  import Vue from 'vue'
//  var bus = new Vue()

  export default{
    data() {
      return {
        articles: [],
        selItems: [],
        loading: false,
        currentPage: 1,
        totalCount: -1,
        pageSize: 6,
        keywords: '',
        dustbinData: [],
        search:{
        userId:1,
        status:1,
        pageNum:1,
        pageSize:10
      },
      }
    },
    mounted: function () {
      this.loading = true;
      this.loadBlogs(1, this.pageSize);
      // window.bus.$on('blogTableReload', function () {
      //   this.loading = true;
      //   this.loadBlogs(this.currentPage, this.pageSize);
      // })
    },
    filters:{
      formatType(val){
        return val==0?"私密文章":'公开文章';
      }
    },
    methods: {
      writeArticle(){
        this.$router.push({path: '/postArticle'})
      },
      searchClick(){
        this.loadBlogs(1, this.pageSize);
      },
      itemClick(row){
        this.$router.push({path: '/blogDetail', query: {aid: row.id}})
      },
      deleteMany(){
        var selItems = this.selItems;
        // console.log(selItems,"selItems");
        
        for (var i = 0; i < selItems.length; i++) {
          this.dustbinData.push(selItems[i].id)
        }
        this.deleteToDustBin(selItems[0].state)
      },
      //翻页
      currentChange(currentPage){
        this.currentPage = currentPage;
        this.loading = true;
        this.loadBlogs(currentPage, this.pageSize);
      },
      loadBlogs(page, count){
        var url = '';
        if (this.state == -2) {
          // url = "/admin/article/all" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
          url = "/api/article/list"
        } else {
          url = "/article/all?state=" + this.state + "&page=" + page + "&count=" + count + "&keywords=" + this.keywords;
        }

        if (this.state == -1) {
          url = "/api/article/getAllArticleByAdmin"
          this.search.search=null;
        }
        //这里发送了多个请求  是面板加载的时候全部一起加载了  这样不合理，浪费资源
        postRequest(url,this.search,this.pageNum).then(resp=> {
          this.loading = false;
          if (resp.status == 200) {
            this.articles = resp.data.data.list;
            console.log("resp",resp);
            // console.log("this.articles",this.articles);
            this.totalCount = resp.data.totalCount;
          } else {
            this.$message({type: 'error', message: '数据加载失败!'});
          }
        }
        )
      },
      handleSelectionChange(val) {
        this.selItems = val;
      },
      handleEdit(index, row) {
        this.$router.push({path: '/editBlog', query: {from: this.activeName,id:row.id}});
      },
      handleDelete(index, row) {
        this.dustbinData.push(row.id);
        this.deleteToDustBin(row.state);
      },
      deleteToDustBin(state){
        console.log(this.state,"this.state");
        
        this.$confirm(state != 2 ? '将该文件放入回收站，是否继续?' : '永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true;
          var url = '';
          if (this.state == -2) {
            url = "api/admin/article/dustbin";
          } else {
            url = "api/article/logicalDel";
          }
          postRequest(url, {aids: this.dustbinData}).then(resp=> {
            if (resp != undefined && resp.data.code == 1000) {
              var data = resp.data;
        // console.log(resp,"resp");
             
              this.$message.success({message:"删除成功"});
                // window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
              
            } else {
              this.$message({type: 'error', message: '删除失败!'});
            }
            this.loading = false;
            this.dustbinData = []
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
          this.dustbinData = []
        });
      },
    },
    props: ['activeName','state'] 
}
</script>
