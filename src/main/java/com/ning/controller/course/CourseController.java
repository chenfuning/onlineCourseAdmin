package com.ning.controller.course;

import com.ning.mapper.CourseSortMapper;
import com.ning.pojo.CourseSort;
import com.ning.pojo.Permission;
import com.ning.result.Results;
import com.ning.service.CourseSortService;
import com.ning.utils.UIDUtil;
import org.apache.ibatis.annotations.Param;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	private CourseSortService courseSortService;
	@Autowired
	private Sid sid;
	/**
	 * 课程分类列表的查询
	 * @return
	 */
	@GetMapping ("/sortlist")
	@ResponseBody
	public Results sortlist() {
		return courseSortService.getSortlist();
	}

	@GetMapping ("/addsort")
	@PreAuthorize("hasAuthority('sys:sort:add')")
	public String addsort(Model model) {
		model.addAttribute("CourseSort",new CourseSort());
		model.addAttribute("CourseSortParent",new CourseSort());
		return "course/sort/sort-add";
	}
	@GetMapping("/fuCourseSortList")
	@ResponseBody
	public Results getfuCourseSortList(){
		return courseSortService.getfuCourseSortList();
	}

	@PostMapping("/sortadd")
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:sort:add')")
	public Results sortadd(String csid,String name, String parentCode){
		CourseSort courseSort=new CourseSort();
		UIDUtil UID=new UIDUtil();
		courseSort.setCode((byte) UID.nextId());
		if(name!=null){
			if(parentCode=="")
				courseSort.setParentCode((byte) 0);
			else
				courseSort.setParentCode(courseSortService.selectCodeByname(parentCode));
			courseSort.setCsid(sid.nextShort());
			courseSort.setName(name);
			courseSort.setCreatetime(new Date());
			courseSortService.sortadd(courseSort);
		    return Results.success();
		}else
		{
			return Results.failure();
		}

	}


	@GetMapping("/editsort")
	@PreAuthorize("hasAuthority('sys:sort:edit')")
	public String editPermission(Model model, String csid) {
		model.addAttribute("CourseSort",courseSortService.getCourseSortBycsid(csid));
		if(courseSortService.getCourseSortBycsid(csid).getParentCode()!=0)
		model.addAttribute("CourseSortParent",courseSortService.getCourseSortNameBycode(courseSortService.getCourseSortBycsid(csid).getParentCode()));
		else
			model.addAttribute("CourseSortParent",new CourseSort());
		return "course/sort/sort-add";
	}

	@RequestMapping(value = "/sortedit", method = RequestMethod.POST)
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:sort:edit')")
	public Results sortedit(String csid,String name, String parentCode) {
		CourseSort courseSort=new CourseSort();
		courseSort.setCsid(csid);
		courseSort.setName(name);
		if(parentCode=="")
			courseSort.setParentCode((byte) 0);
		else
			courseSort.setParentCode(courseSortService.selectCodeByname(parentCode));
		return courseSortService.updateCourseSort(courseSort);
	}

	@RequestMapping(value = "/deletesort", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:sort:del')")
	public Results deletesort(String csid) {
		return courseSortService.delete(csid);
	}
}
