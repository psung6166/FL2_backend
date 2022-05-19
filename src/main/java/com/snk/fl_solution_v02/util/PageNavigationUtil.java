package com.snk.fl_solution_v02.util;

import java.util.HashMap;

import org.springframework.ui.ModelMap;

import com.snk.fl_solution_v02.domain.PageNavigationVo;
import com.snk.fl_solution_v02.object.HMap;

public class PageNavigationUtil {

	public static HMap createNavigationInfo(ModelMap model, HMap hMap) {
		PageNavigationVo pnv = new PageNavigationVo();

		pnv.setPageInputName(hMap.getString("pageInputName","currentPage"));
		pnv.setPageCallFunction(hMap.getString("pageCallFunction","fnGoPage"));
		
		pnv.setTotalCount(hMap.getInt("totalCount"));
		
		pnv.setRowPerPage(Integer.parseInt(hMap.getString("rowPerPage", hMap.getString("sch_row_per_page", "10"))));

		pnv.setCurrentPage(Integer.parseInt(hMap.getString(pnv.getPageInputName(), "1")));
		pnv.setNaviCount(Integer.parseInt(hMap.getString("naviCount", "5")));

		int lastPage = pnv.getTotalCount() / pnv.getRowPerPage();
		int dummyPage = 0;
		if (pnv.getTotalCount() % pnv.getRowPerPage() > 0) { //나머지가 존재할경우 1페이지 추가
			dummyPage = 1;
		}
		pnv.setLastPage(lastPage + dummyPage);
		int plusPage = pnv.getCurrentPage() % pnv.getNaviCount() == 0 ? -1 * pnv.getNaviCount() + 1 : 1;
		pnv.setFirstPage(pnv.getCurrentPage() / pnv.getNaviCount() * pnv.getNaviCount() + plusPage);
		pnv.setCurrDataNo(pnv.getTotalCount() - ((pnv.getCurrentPage() - 1) * pnv.getRowPerPage()));

		model.addAttribute("pageNavigationVo", pnv);

		hMap.put("limitStart", (pnv.getCurrentPage() - 1) * pnv.getRowPerPage());
		hMap.put("limitCount", pnv.getRowPerPage());

		// mysql 경우 limit 이용시 끝에는 한페이지에 보여줄 게시물수만 있으면 된다.
		hMap.put("limitEnd", pnv.getRowPerPage());

		String naviBar = createNavigationBar(pnv);
		// 페이지 관련 태그 스트링 등록
		model.addAttribute("navigationBar", naviBar);
		hMap.put("navigationBar", naviBar);

		return hMap;
	}
	
	public static String createNavigationBar(PageNavigationVo pnv) {

		StringBuffer rtnStr = new StringBuffer();
		int nextPage = 0;

		if (pnv.getTotalCount() > 0) {
			rtnStr.append("<nav aria-label=\"Page navigation\">");
			rtnStr.append("<ul class=\"pagination pagination-sm no-margin justify-content-center\">");

			if (pnv.getFirstPage() + pnv.getNaviCount() > pnv.getLastPage()) {
				nextPage = pnv.getLastPage() + 1;
			} else {
				nextPage = pnv.getFirstPage() + pnv.getNaviCount();
			}

			rtnStr.append("<li class=\"page-item\"><a href=\"#\" class=\"page-link\" title=\"맨앞으로가기\" onclick=\"" + pnv.getPageCallFunction() + "('1'); return false;\">&laquo;</a></li>");

			if (pnv.getFirstPage() > pnv.getNaviCount()) {
				rtnStr.append("<li class=\"page-item\"><a href=\"#\" class=\"page-link\" title=\"앞으로가기\" onclick=\"" + pnv.getPageCallFunction() + "('" + (pnv.getFirstPage() - 1)
						+ "'); return false;\">&lt;</a></li>");
			}

			for (int i = pnv.getFirstPage(); i < nextPage; i++) {
				if (pnv.getCurrentPage() == i) {
					rtnStr.append("<li class=\"page-item\"><a href=\"#\" class=\"page-link active \" onclick=\"" + pnv.getPageCallFunction() + "('" + i + "'); return false;\">" + i + "</a></li>");
				} else {
					rtnStr.append("<li class=\"page-item\"><a href=\"#\" class=\"page-link\" onclick=\"" + pnv.getPageCallFunction() + "('" + i + "'); return false;\">" + i + "</a></li>");
				}
			}

			if (pnv.getFirstPage() + pnv.getNaviCount() - 1 < pnv.getLastPage()) {
				rtnStr.append("<li class=\"page-item\"><a href=\"#\" class=\"page-link\" title=\"뒤로가기\" onclick=\"" + pnv.getPageCallFunction() + "('" + (pnv.getFirstPage() + pnv.getNaviCount())
						+ "'); return false;\">&gt;</a></li>");
			}

			rtnStr.append("<li class=\"page-item\"><a href=\"#\" class=\"page-link\" title=\"맨뒤로가기\" onclick=\"" + pnv.getPageCallFunction() + "('" + pnv.getLastPage()
					+ "'); return false;\">&raquo;</a></li>");
			rtnStr.append("</ul>");
			rtnStr.append("</nav>");

			rtnStr.append("<input type=\"hidden\" name=\"" + pnv.getPageInputName() + "\" id=\"" + pnv.getPageInputName() + "\" value=\"" + pnv.getCurrentPage() + "\"/>");
		}

		return rtnStr.toString();
	}
	
	
}
