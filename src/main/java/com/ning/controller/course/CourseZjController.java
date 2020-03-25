package com.ning.controller.course;

import com.ning.pojo.CourseZj;
import com.ning.result.Results;
import com.ning.service.CourseZjService;
import com.ning.utils.FastDFSClient;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("coursezj")
public class CourseZjController {
    @Autowired
    private CourseZjService courseZjService;
    @Autowired
    private Sid sid;
    @Autowired
    private FastDFSClient fastDFSClient;

    @GetMapping("/zjlist")
    public String zjlist(Model model, String cid) {
        model.addAttribute("cid", cid);
        return "course/course/coursezj-list";
    }

    /**
     * 课程分类列表的查询
     *
     * @return
     */
    @GetMapping("/coursezjlist")
    @ResponseBody
    public Results coursezjlist(String cid) {
        return Results.success(0, courseZjService.getCourseZlistsBycid(cid));
    }

    @GetMapping("/jielist")
    public String jielist(Model model, String sort, String cid) {
        model.addAttribute("sort", sort);
        model.addAttribute("cid", cid);
        return "course/course/coursezj-addjie";
    }

    /**
     * 加载节
     *
     * @param sort
     * @return
     */
    @GetMapping("/getjielist")
    @ResponseBody
    public Results getjielist(String sort, String cid) {
        return Results.success(0, courseZjService.getCourseJlistsBysort(sort, cid));
    }


    @GetMapping("/addZ")
    @ResponseBody
    public Results addZ(String cid) {
        CourseZj courseZj = new CourseZj();
        courseZj.setZid(sid.nextShort());
        courseZj.setCourseId(cid);
        courseZj.setParentId("0");
        //查询cid课程下面已有多少章
        int count = courseZjService.getCountbyCid(cid);
        courseZj.setSort((byte) (count + 1));
        courseZj.setName("第"+numchangtool(count+1)+"章");
        courseZj.setCreatetime(new Date());
        courseZjService.addcoursezj(courseZj);
        return Results.success();
    }

    /**
     * 把阿拉伯数字转为中文
     * @param count
     * @return
     */
    String numchangtool(int count) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};

        String result = "";
        String string = String.valueOf(count);
        int n = string.length();
        for (int i = 0; i < n; i++) {

            int num = string.charAt(i) - '0';

            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        System.out.println(result);
        return result;
    }

    @GetMapping("/delZ")
    @ResponseBody
    public Results delZ(String cid) {
        courseZjService.delcoursezj(cid);
        return Results.success();
    }

    @GetMapping("/addJ")
    @ResponseBody
    public Results addJ(String cid,String sort) {
        CourseZj courseZj = new CourseZj();
        courseZj.setZid(sid.nextShort());
        courseZj.setCourseId(cid);
        courseZj.setParentId(sort);
        //查询cid课程下面已有多少章
        int count = courseZjService.getCountJbyCidAndsort(cid,sort);
        courseZj.setSort((byte) (count + 2));
        courseZj.setName("待编辑");
        courseZj.setCreatetime(new Date());
        courseZjService.addcoursezj(courseZj);
        return Results.success();
    }

    @GetMapping("/lookvedio")
    public String lookvedio(Model model, String zid) {
        model.addAttribute("CourseZJ", courseZjService.getcourseJByzid(zid));
        return "course/course/vedio";
    }

    @PostMapping("/uploadcoursevideo")
    @ResponseBody
    public Results uploadcoursevideo(MultipartFile file, HttpServletRequest request) throws IOException {
        String url=fastDFSClient.uploadFile(file);
        //String url="M00/00/00/wKjzgV5U0qWAaZrAAF4YXJV8uAU780_big.mp4";
        //http://192.168.243.129:88/ning/M00/00/00/wKjzgV5U0qWAaZrAAF4YXJV8uAU780_big.mp4
        return Results.success(url);
    }

    @PostMapping("/updateVideourl")
    @ResponseBody
    public Results updateVideourl(String zid,String videoUrl) {
        if(videoUrl!=null&&videoUrl!="") {
            CourseZj courseZj = new CourseZj();
            courseZj.setZid(zid);
            courseZj.setVideoUrl(videoUrl);
            courseZjService.updateVideoUrl(courseZj);
            return Results.success();
        }else
            return  Results.failure();

    }
    @GetMapping("/savejie")
    @ResponseBody
    public Results savejie(String zid,String name,String videoUrl) {
        if(videoUrl!=null&&videoUrl!="") {
            if(name!=null&&name!=""){
                CourseZj courseZj = new CourseZj();
                courseZj.setZid(zid);
                courseZj.setName(name);
                courseZj.setVideoUrl(videoUrl);
                courseZjService.updateVideoUrl(courseZj);
            }
            return Results.success();
        }else
            return  Results.failure();

    }

    @GetMapping("/deletejie")
    @ResponseBody
    public Results deletejie(String zid) {
        courseZjService.delcoursezjByzid(zid);
        return Results.success();
    }

}
