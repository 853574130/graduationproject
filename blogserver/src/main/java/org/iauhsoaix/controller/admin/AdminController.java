package org.iauhsoaix.controller.admin;

import org.iauhsoaix.oldbean.Article;
import org.iauhsoaix.oldbean.RespBean;
import org.iauhsoaix.service.ArticleService;
import org.iauhsoaix.utils.Pager;
import org.iauhsoaix.utils.Result;
import org.iauhsoaix.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 超级管理员专属Controller
 */

//@RequestMapping("/admin")
@RestController
public class AdminController {
//    extends BaseController<EquipmentManageEntity,EquipmentManageInfo>
    @Autowired
    ArticleService articleService;

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public Result<Void> login(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                              @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
//        return ResultUtils.success();
//    }


//    @RequestMapping(value = "/article/all", method = RequestMethod.GET)
//    public Map<String, Object> getArticleByStateByAdmin(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "count", defaultValue = "6") Integer count, String keywords) {
//        List<Article> articles = articleService.getArticleByState(-2, page, count, keywords);
//        Map<String, Object> map = new HashMap<>();
//        map.put("articles", articles);
//        map.put("totalCount", articleService.getArticleCountByState(1, null, keywords));
//        return map;
//    }

//    @RequestMapping(value = "/article/dustbin", method = RequestMethod.PUT)
    public RespBean updateArticleState(Long[] aids, Integer state) {
        if (articleService.updateArticleState(aids, state) == aids.length) {
            return new RespBean("success", "删除成功!");
        }
        return new RespBean("error", "删除失败!");
    }
}
