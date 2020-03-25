package com.ning.controller.course;

import com.ning.mapper.CommentMapper;
import com.ning.pojo.Admin;
import com.ning.pojo.Comment;
import com.ning.result.PageTableRequest;
import com.ning.result.Results;
import com.ning.service.CommentService;
import com.ning.service.CourseZjService;
import com.ning.utils.FastDFSClient;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("coursedata")
public class CourseDataController {
    @Autowired
    private CommentService commentService;

    /**
     * 分页查询User
     * @param pageTableRequest
     * @return
     */
    @GetMapping("/commentlist")
    @ResponseBody
    public Results<Comment> commentlist(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return commentService.getAllCommentsByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    @GetMapping("/commentdelete")
    @ResponseBody
    public Results commentdelete( String commentid){
        commentService.deletecommentBycommentid(commentid);
        return Results.success();
    }
}
