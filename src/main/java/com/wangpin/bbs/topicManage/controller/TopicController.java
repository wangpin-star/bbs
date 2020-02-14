package com.wangpin.bbs.topicManage.controller;

import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.service.implement.TopicServiceImpl;
import com.wangpin.bbs.userManage.bean.User;
import com.wangpin.bbs.userManage.service.implement.UserServiceImpl;
import com.wangpin.bbs.utils.ResultDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/topicManage")
public class TopicController {
    @Autowired
    private TopicServiceImpl topicServiceImpl;

    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping({"/toAddTopic"})
    public String toAddTopic(Model model, HttpServletRequest request ) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if (user==null)
            return "redirect:/userManage/home?name="+user.getUserName();

        else{
            model.addAttribute("user",user);
            return "jie/add";
        }

    }

    @PostMapping({"/saveTopic"})
    @ResponseBody
    public ResultDomain saveTopic(Model model, HttpServletRequest request, Topic topic) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        ResultDomain resultDomain=new ResultDomain();
        if (user==null){
            topic.setCreateTime(new Date());
            topic.setTopicState("正常");
            topic.setOwnerId(user.getId());
            topic.setOwnerName(user.getNickname());
            return resultDomain=topicServiceImpl.addTopic(topic);

        }
        resultDomain.setResultMsg("非法操作！请登录后重试！");
        resultDomain.setResultCode(-1);
        return resultDomain;
    }

}
