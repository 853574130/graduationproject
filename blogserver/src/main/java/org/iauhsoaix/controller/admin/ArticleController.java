package org.iauhsoaix.controller.admin;

import org.apache.commons.io.IOUtils;
import org.iauhsoaix.bean.ArticleInfo;
import org.iauhsoaix.controller.BaseController;
import org.iauhsoaix.dal.entity.ArticleEntity;
import org.iauhsoaix.exceptions.CommonBusinessException;
import org.iauhsoaix.oldbean.Article;
import org.iauhsoaix.oldbean.RespBean;
import org.iauhsoaix.service.ArticleService;
import org.iauhsoaix.service.BaseService;
import org.iauhsoaix.utils.Pager;
import org.iauhsoaix.utils.Result;
import org.iauhsoaix.utils.ResultUtils;
import org.iauhsoaix.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Edited by iauhsoaix on 2017/12/20.
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleEntity, ArticleInfo> {

    @Override
    protected BaseService<ArticleEntity, ArticleInfo> getBaseService() {
        return articleService;
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public RespBean addNewArticle(Article article) {
        int result = articleService.addNewArticle(article);
        if (result == 1) {
            return new RespBean("success", article.getId() + "");
        } else {
            return new RespBean("error", article.getStatus() == 0 ? "文章保存失败!" : "文章发表失败!");
        }
    }

    /**
     * 上传图片
     *
     * @return 返回值为图片的地址
     */
    @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
    public RespBean uploadImg(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        String filePath = "/blogimg/" + sdf.format(new Date());
        String imgFolderPath = req.getServletContext().getRealPath(filePath);
        File imgFolder = new File(imgFolderPath);
        if (!imgFolder.exists()) {
            imgFolder.mkdirs();
        }
        url.append(req.getScheme())
                .append("://")
                .append(req.getServerName())
                .append(":")
                .append(req.getServerPort())
                .append(req.getContextPath())
                .append(filePath);
        String imgName = UUID.randomUUID() + "_" + image.getOriginalFilename().replaceAll(" ", "");
        try {
            IOUtils.write(image.getBytes(), new FileOutputStream(new File(imgFolder, imgName)));
            url.append("/").append(imgName);
            return new RespBean("success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RespBean("error", "上传失败!");
    }

    /**
     * @Author:iauhsoaix
     * @date 2018/12/5
     * @Description:这个方法后期要废弃
     */
    @RequestMapping(value = "/adminArticlList")
    public Result<Pager<ArticleInfo>> getAdminArticlList(ArticleInfo search,Integer pageNum) {
//        List allList= articleService.getListBy(search);
        return null;
    }


    @RequestMapping(value = "/{aid}", method = RequestMethod.GET)
    public Article getArticleById(@PathVariable Long aid) {
        return articleService.getArticleById(aid);
    }

    @RequestMapping(value = "/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }

    @RequestMapping("/dataStatistics")
    public Map<String, Object> dataStatistics() {
        Map<String, Object> map = new HashMap<>();
        List<String> categories = articleService.getCategories();
        List<Integer> dataStatistics = articleService.getDataStatistics();
        map.put("categories", categories);
        map.put("ds", dataStatistics);
        return map;
    }

    //---------------------------游客接口-------------------------------
    @RequestMapping(value = "/publicArticle", method = RequestMethod.POST)
    public Result<Pager<ArticleInfo>> visitorGetArticle(ArticleInfo search) {
        List<ArticleInfo> list = articleService.getListBy(search);
        Pager<ArticleInfo> pager = new Pager(1, 20, 1, list);
//        throw new CommonBusinessException("异常测试");
//        return ResultUtils.failure("异常测试没通过");
        return ResultUtils.success(pager);
    }

    @RequestMapping(value = "/publicArticleDetial", method = RequestMethod.GET)
    public Result<ArticleInfo> visitorGetArticleDetial(Long id) {
        ArticleInfo info = articleService.getModel(id);
        return ResultUtils.success(info);
    }


}
