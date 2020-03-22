package com.ning.controller.course;

import com.ning.mapper.CourseSortMapper;
import com.ning.pojo.Admin;
import com.ning.pojo.Course;
import com.ning.pojo.CourseSort;
import com.ning.pojo.Dto.CourseDto;
import com.ning.pojo.Permission;
import com.ning.result.PageTableRequest;
import com.ning.result.Results;
import com.ning.service.CourseService;
import com.ning.service.CourseSortService;
import com.ning.utils.FastDFSClient;
import com.ning.utils.UIDUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("course")
public class CourseController {
	@Autowired
	private CourseSortService courseSortService;
	@Autowired
	private Sid sid;
	@Autowired
	private CourseService courseService;
	@Autowired
	private FastDFSClient fastDFSClient;
	/**
	 * 课程分类列表的查询
	 * @return
	 */
	@GetMapping ("/sortlist")
	@ResponseBody
	public Results sortlist() {
		return courseSortService.getSortlist();
	}

	/**
	 * 跳转到addcoursesort页面
	 * @param model
	 * @return
	 */
	@GetMapping ("/addsort")
	@PreAuthorize("hasAuthority('sys:sort:add')")
	public String addsort(Model model) {
		model.addAttribute("CourseSort",new CourseSort());
		model.addAttribute("CourseSortParent",new CourseSort());
		return "course/sort/sort-add";
	}

	/**
	 * 查询父级对象
	 * @return
	 */
	@GetMapping("/fuCourseSortList")
	@ResponseBody
	public Results getfuCourseSortList(){
		return courseSortService.getfuCourseSortList();
	}

	/**
	 * 添加coursesort
	 * @param csid
	 * @param name
	 * @param parentCode
	 * @return
	 */
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

	/**
	 * 跳转编辑页面
	 * @param model
	 * @param csid
	 * @return
	 */
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

	/**
	 * 编辑coursesort
	 * @param csid
	 * @param name
	 * @param parentCode
	 * @return
	 */
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

	/**
	 * 删除coursesort
	 * @param csid
	 * @return
	 */
	@RequestMapping(value = "/deletesort", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasAuthority('sys:sort:del')")
	public Results deletesort(String csid) {
		return courseSortService.delete(csid);
	}


	/**
	 * 分页查询课程列表
	 * @param pageTableRequest
	 * @return
	 */
	@GetMapping("/courselist")
	@ResponseBody
	public Results<Course> courselist(PageTableRequest pageTableRequest){
		pageTableRequest.countOffset();
		return courseService.getAllCoursesByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
	}

	@GetMapping("/addcourse")
	public String addcourse(Model model){
		model.addAttribute("Course",new Course());
		model.addAttribute("CodeName",null);
		model.addAttribute("parentCodeName",null);
		return "course/course/course-add";
	}

	@GetMapping("/getcode")
	@ResponseBody
	public Results<CourseSort> getcode( String parentCodeName){
		return Results.success(0,courseSortService.getSeCourseSortsByparentCode(parentCodeName));
	}

	@PostMapping("/uploadcourseimg")
	@ResponseBody
	public Results uploadcourseimg(MultipartFile file, HttpServletRequest request) throws IOException {
		String url=fastDFSClient.uploadBase64(file);
		return Results.success(url);
	}
	@PostMapping("/addcourse")
	@ResponseBody
	public Results addcourse(@RequestBody CourseDto courseDto){
		courseDto.setCid(sid.nextShort());
		String tempo= courseSortService.selectCodeByname(courseDto.getCode()).toString();
		courseDto.setClassify(tempo);
		courseDto.setOnsole("false");
		courseDto.setCreatetime(new Date());
		courseService.addcourse(courseDto);
		return Results.success();
	}

	@GetMapping("/courseedit")
	public  String courseedit(Model model,String cid){
		model.addAttribute("Course",courseService.getCourseBycid(cid));
		byte Code=(byte)Integer.parseInt(courseService.getCourseBycid(cid).getClassify());
		String codename=courseSortService.getCourseSortNameBycode(Code).getName();
		String parentCodename=courseSortService.getCourseSortNameBycode(courseSortService.getCourseSortNameBycode(Code).getParentCode()).getName();
		model.addAttribute("CodeName",codename);
		model.addAttribute("parentCodeName",parentCodename);
		return "course/course/course-add";
	}

	@PostMapping("/editcoursepost")
	@ResponseBody
	public Results editcoursepost(@RequestBody CourseDto courseDto){
		if(courseDto.getImgurl()==""||courseDto.getImgurl()==null){
			courseDto.setImgurl(courseService.getCourseBycid(courseDto.getCid()).getImgurl());
		}else{
			//有存了新的图片，删除旧图
			fastDFSClient.deleteFile(courseService.getCourseBycid(courseDto.getCid()).getImgurl());
		}
		return courseService.updata(courseDto);
	}
	@GetMapping("/deletecourse")
	@ResponseBody
	public Results deletecourse(String cid){
		fastDFSClient.deleteFile(courseService.getCourseBycid(cid).getImgurl());
		int result=courseService.deletecourse(cid);
		if(result==1)
		return Results.success();
		else
		return Results.failure();
	}
}
