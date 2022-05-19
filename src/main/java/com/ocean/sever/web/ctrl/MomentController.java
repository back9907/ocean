package com.ocean.sever.web.ctrl;

import com.ocean.sever.config.CommonResult;
import com.ocean.sever.web.logic.spi.MomentLogic;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author back
 */
@Slf4j
@RestController
@RequestMapping(value = "/ocean/api/moment")
public class MomentController {

    @Autowired
    MomentLogic momentLogic;

    @ApiOperation("根据userId查询Moment")
    @GetMapping(value = "/id")
    public CommonResult findMomentByUserId(@RequestParam("user_id")long userId, @RequestParam("pageNum") int pageNum) {
        return momentLogic.findAllMomentByUserId(userId,pageNum)!=null
                ?CommonResult.successWithData(momentLogic.findAllMomentByUserId(userId,pageNum))
                : CommonResult.result(403,"no moment",null);
    }

    @ApiOperation("根据moment id 点赞")
    @PatchMapping(value = "/like")
    public CommonResult addLikeForAMoment(@RequestParam("user_id")long userId,@RequestParam("moment_id")long momentId) {
        boolean result = momentLogic.addLikeForAMoment(userId,momentId);
        return result ?CommonResult.successWithData(result) : CommonResult.result(403,"like fail",null);
    }

    @ApiOperation("根据moment id 取消赞")
    @PatchMapping(value = "/cancel")
    public CommonResult cancelLikeForAMoment(@RequestParam("user_id")long userId,@RequestParam("moment_id")long momentId) {
        boolean result = momentLogic.cancelLikeForAMoment(userId,momentId);
        return result ?CommonResult.successWithData(result) : CommonResult.result(403,"cancel fail",null);
    }

    @ApiOperation("根据时间倒序查询Moment")
    @GetMapping(value = "/all")
    public CommonResult findAllMoment( @RequestParam("pageNum") int pageNum, @RequestParam("userId") long userId) {
        return momentLogic.findAllMoment(pageNum,userId)!=null
                ?CommonResult.successWithData(momentLogic.findAllMoment(pageNum,userId))
                : CommonResult.result(403,"no moment",null);
    }

    @ApiOperation("判断是否点赞")
    @GetMapping(value = "/iflike")
    public CommonResult ifUserLikeThisMoment(@RequestParam("user_id")long userId,@RequestParam("moment_id")long momentId){
        return CommonResult.successWithData(momentLogic.ifUserLikeThisMoment(userId,momentId));
    }

    @ApiOperation("新发布moment")
    @PostMapping(value = "/post")
    public CommonResult postANewMoment(@RequestParam("user_id")long userId, @RequestParam("content")String content,
                            @RequestParam("image") String image, @RequestParam("private_level") int private_level,
                            @RequestParam("author_name")String author_name){

        return CommonResult.successWithData(momentLogic.postANewMoment(userId, content, image, private_level,author_name));
    }

    @ApiOperation("获取别人给你的点赞")
    @GetMapping(value = "/system")
    public CommonResult getLikeInfo(@RequestParam("user_id") long userId){
        return CommonResult.successWithData(momentLogic.getLikeInfo(userId));
    }

}