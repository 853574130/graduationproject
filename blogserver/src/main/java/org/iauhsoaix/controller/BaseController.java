package org.iauhsoaix.controller;


import org.iauhsoaix.bean.BaseInfo;
import org.iauhsoaix.dal.entity.AbstractEntity;
import org.iauhsoaix.service.BaseService;
import org.iauhsoaix.utils.*;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public abstract class BaseController<T extends AbstractEntity, I extends BaseInfo> implements LogCapability {
	protected Logger logger = getLogger();
	protected static final int DEFAULT_PAGE_SIZE = Constant.DEFAULT_PAGE_SIZE;

	protected abstract BaseService<T, I> getBaseService();

	@RequestMapping("list")
	public Result<Pager<I>> list(int pageNum, I info) {
		ExchangeUtils.convertBlank2Null(info);
		Pager<I> pager = getBaseService().getModelList(pageNum, DEFAULT_PAGE_SIZE, info);
		return ResultUtils.success(pager);
	}
//
//	@RequestMapping("like")
//	public Result<Pager<I>> listByLike(int pageNum, int pageSize, I info) {
//		ExchangeUtils.convertBlank2Null(info);
//		Pager<I> pager = getBaseService().getModelListByLike(pageNum, pageSize, info, false);
//		return ResultUtils.success(pager);
//	}

	@RequestMapping("getById")
	public Result<I> getById(Long id){
		return ResultUtils.success(getBaseService().getModel(id));
	}
	
//	@RequestMapping("get")
//	public Result<List<I>> list() {
//		return ResultUtils.success(getBaseService().getValidList());
//	}

	@RequestMapping("/saveOrUpdate")
	public Result<I> saveOrUpdate(I info) {
		return ResultUtils.success(getBaseService().saveOrUpdate(info));
	}

	@RequestMapping("insert")
	public Result<Void> insertBy(I info) {
		getBaseService().insertBy(info);
		return ResultUtils.success();
	}

	@RequestMapping("update")
	public Result<Void> updateByPrimaryKey(I info) {
		getBaseService().updateByPrimaryKeySelective(info);
		return ResultUtils.success();
	}

	/**
	 * 物理删除，谨慎使用
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	public Result<Void> deleteByPrimaryKey(Integer id) {
		getBaseService().deleteBy(id);
		return ResultUtils.success();
	}

//	protected List<? extends BaseTemplate> getListForDownload() {
//		return null;
//	}
//
//	protected List<? extends BaseTemplate> getTemplateForDownload() {
//		return null;
//	}

	protected void uploadData(MultipartFile file) throws IOException {

	}

//	@RequestMapping("export/list")
//	public void exportList(HttpServletRequest request, HttpServletResponse response) {
//		response.setCharacterEncoding(request.getCharacterEncoding());
//		response.setContentType("application/octet-stream");
//		FileInputStream fis = null;
//		try {
//			File file = File.createTempFile("数据列表", ".xls");
//			List<? extends BaseTemplate> list = getListForDownload();
//			ExcelExportUtil.exportToFile(file.getPath(), list);
//			fis = new FileInputStream(file);
//			IOUtils.copy(fis, response.getOutputStream());
//			response.flushBuffer();
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		} finally {
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

//	@RequestMapping("export/template")
//	public void exportTemplate(HttpServletRequest request, HttpServletResponse response) {
//		response.setCharacterEncoding(request.getCharacterEncoding());
//		response.setContentType("application/octet-stream");
//		FileInputStream fis = null;
//		try {
//			File file = File.createTempFile("数据模板", ".xls");
//			List<? extends BaseTemplate> list = getTemplateForDownload();
//			ExcelExportUtil.exportToFile(file.getPath(), list);
//			fis = new FileInputStream(file);
//			IOUtils.copy(fis, response.getOutputStream());
//			response.flushBuffer();
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		} finally {
//			if (fis != null) {
//				try {
//					fis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	@RequestMapping("upload")
	public Result<Void> upload(MultipartFile file) {
		if (file != null) {
			try {
				uploadData(file);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return ResultUtils.failure("上传信息失败:" + e.getMessage());
			}
		}
		return ResultUtils.success();
	}
}
