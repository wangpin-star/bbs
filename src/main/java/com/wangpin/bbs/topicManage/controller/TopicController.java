package com.wangpin.bbs.topicManage.controller;

import com.wangpin.bbs.topicManage.bean.Reply;
import com.wangpin.bbs.topicManage.bean.Topic;
import com.wangpin.bbs.topicManage.service.implement.TopicServiceImpl;
import com.wangpin.bbs.userManage.bean.Collect;
import com.wangpin.bbs.userManage.bean.User;
import com.wangpin.bbs.userManage.service.implement.UserServiceImpl;
import com.wangpin.bbs.utils.PageResult;
import com.wangpin.bbs.utils.ResultDomain;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/topicManage")
public class TopicController {
    @Autowired
    private TopicServiceImpl topicServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    private InputStream inputStream;

    String path;//图片要保存的路径
    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping({"/toAddTopic"})
    public String toAddTopic(Model model, HttpServletRequest request ) {
        HttpSession session=request.getSession(true);
        User user= (User) session.getAttribute("user");
//        if (user==null)
//            return "redirect:/userManage/login";
//
//        else{
            model.addAttribute("user",user);
            return "jie/add";
//        }

    }

    public void deleteAllFile(File file){
        File[] files = file.listFiles();
        if (files != null && files.length >0){
            for (File v:files){
                deleteAllFile(v);
            }
        }
        file.delete();
    }

        //资源上传
    @PostMapping (value="/uploadTopicImg")
    @ResponseBody//json交互注解
    public Map<String, Object> uploadResource(HttpServletRequest request, HttpServletResponse response, HttpSession session, MultipartFile file) throws Exception{
        User user = (User) session.getAttribute("user");
        String prefix="";
        String dateStr="";
        //保存上传
        if(user==null){
            Map<String,Object> map=new HashMap<>();
            map.put("code",-2);
            map.put("msg","用户尚未登录！");
            return map;
        }
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                dateStr = simpleDateFormat.format(date);
                path="C:\\Users\\1\\IdeaProjects\\bbs\\topicImg\\" +dateStr+"\\"+uuid+"." + prefix;
                byte [] byteArr=file.getBytes();
                inputStream= new ByteArrayInputStream(byteArr);
                Map<String,Object> map=new HashMap<>();
                map.put("code",1);
                map.put("msg","上传成功！");
                map.put("src","/topicImg/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",-1);
        map.put("msg","上传失败！");
        return map;
    }


    @PostMapping({"/saveTopic"})
    @ResponseBody
    public ResultDomain saveTopic(Model model, HttpServletRequest request, Topic topic)  {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        System.out.println(topic);
        String prefix="";
        String dateStr="";
        String []module_name={" ","提问","分享","讨论","建议","公告"};
        System.out.println(module_name);
        ResultDomain resultDomain=new ResultDomain();
        if (user!=null){
            topic.setCreateTime(new Date());
            topic.setTopicState("正常");
            topic.setOwnerId(user.getId());
            topic.setOwnerName(user.getNickname());
            topic.setModuleName(module_name[topic.getModuleId()]);
            topic.setUserimg(user.getImage());
            resultDomain=topicServiceImpl.addTopic(topic);
            if (resultDomain.getResultCode()>0){
               if (path!=null){
                   File files=new File(path);
                   //打印查看上传路径
                   System.out.println(path);
                   if(!files.getParentFile().exists()){
                       files.getParentFile().mkdirs();
                   }
                   try{
                       OutputStream out = new FileOutputStream(path);
                       byte[] buff = new byte[1024];
                       int len = 0;
                       while((len=inputStream.read(buff))!=-1){
                           out.write(buff, 0, len);
                       }
                       inputStream.close();
                       out.close();
                       inputStream=null;
                       out=null;
                       path=null;
                   }catch (Exception e){
                       log.info(e.getMessage());
                   }
               }
                User user2=new User();
                user2.setId(user.getId());
                user2.setGold(user.getGold()-topic.getGold());
                user2.setTopicNum(topicServiceImpl.countTopicByUid(user.getId()));
                userServiceImpl.userInfoUpload(user2);
            }
            return resultDomain;
        }
        resultDomain.setResultMsg("非法操作！请登录后重试！");
        resultDomain.setResultCode(-1);
        return resultDomain;
    }


    @PostMapping({"/saveReply"})
    @ResponseBody
    public ResultDomain saveReply(Model model, HttpServletRequest request, Reply reply) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        ResultDomain resultDomain=new ResultDomain();
        if (user!=null){
            reply.setTime(new Date());
            reply.setUserId(user.getId());
            reply.setUserName(user.getNickname());
            reply.setUserImg(user.getImage());
            resultDomain=topicServiceImpl.addReply(reply);
            if (resultDomain.getResultCode()>0){
                if (path!=null){
                    File files=new File(path);
                    //打印查看上传路径
                    System.out.println(path);
                    if(!files.getParentFile().exists()){
                        files.getParentFile().mkdirs();
                    }
                    try{
                        OutputStream out = new FileOutputStream(path);
                        byte[] buff = new byte[1024];
                        int len = 0;
                        while((len=inputStream.read(buff))!=-1){
                            out.write(buff, 0, len);
                        }
                        path=null;
                        inputStream.close();
                        out.close();
                        inputStream=null;
                        out=null;
                    }catch (Exception e){
                        log.info(e.getMessage());
                    }
                }
                user.setReplyNum(topicServiceImpl.countReplyByUid(user.getId()));
                userServiceImpl.userInfoUpload(user);
                Topic topic=new Topic();
                topic.setId(reply.getTopicId());
                topic.setReplyNum(topicServiceImpl.countReplyByTopicId(topic.getId()));
                topic.setLastReplyId(reply.getUserId());
                topic.setLastReplyName(reply.getUserName());
                topic.setLastReplyTime(reply.getTime());
                topicServiceImpl.updateTopic(topic);
            }
            return resultDomain;
        }
        resultDomain.setResultMsg("非法操作！请登录后重试！");
        resultDomain.setResultCode(-1);
        return resultDomain;
    }


    @PostMapping({"/acceptReply"})
    @ResponseBody
    public ResultDomain acceptReply(Model model, HttpServletRequest request,int replyId,int topicId,int gold) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        ResultDomain resultDomain=new ResultDomain();
        if (user!=null){
            Reply reply=new Reply();
            Topic topic=new Topic();
            reply.setId(replyId);
            reply.setAccept(1);
            topic.setId(topicId);
            topic.setEnd(1);
            user.setGold(user.getGold()+gold);
            int code1=topicServiceImpl.updateReply(reply).getResultCode();
            int code2=userServiceImpl.userInfoUpload(user).getResultCode();
            int code3=topicServiceImpl.updateTopic(topic).getResultCode();
            if (code1>0&&code2>0&&code3>0) {
                resultDomain.setResultMsg("采纳成功！");
                resultDomain.setResultCode(1);
            }
            else{
                resultDomain.setResultMsg("采纳失败！");
                resultDomain.setResultCode(-1);
            }
            return resultDomain;
        }
        resultDomain.setResultMsg("非法操作！请登录后重试！");
        resultDomain.setResultCode(-1);
        return resultDomain;
    }


    @RequestMapping({"/column"})
    public String getAllTopic(Model model, HttpServletRequest request, @RequestParam(value ="page",defaultValue ="0") int page, String moduleName,@RequestParam(value ="category",defaultValue ="综合") String category) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        if (page<0)
            page=0;

        Integer end = null;
        Integer essence = null;
        String moduleName1="all";
        List<Topic> mostPopularTopics=topicServiceImpl.queryTopicOrderByReplyNum().getResultData();
        if (moduleName.equalsIgnoreCase("all"))
            moduleName=null;
        else
            moduleName1=moduleName;
        if (category.compareToIgnoreCase("综合")==0){
           end=null;
           essence=null;
        }
        if (category.compareToIgnoreCase("已结")==0){
            end=1;
        }
        if (category.compareToIgnoreCase("未结")==0){
            end=0;
        }
        if (category.compareToIgnoreCase("精贴")==0){
            essence=1;
        }
        model.addAttribute("user",user);
        model.addAttribute("mostPopularTopics",mostPopularTopics);
        if (user!=null){
            log.info(user.toString());
            model.addAttribute("loginResult",1);
        }
        else {
            log.info("未登录！");
            model.addAttribute("loginResult",-1);
        }
        int totalPage=topicServiceImpl.count(moduleName,end,essence,0)/10+1;
        List<Topic> topics=topicServiceImpl.queryModuleTopic(moduleName,end,essence,page,0,10).getResultData();//page从0开始
        log.info("当前页面："+page);
        log.info("总页面："+totalPage);
        model.addAttribute("pageResult", PageResult.createPage(totalPage,page));
        model.addAttribute("thisModule",moduleName1);
        model.addAttribute("category",category);
        model.addAttribute("topics",topics);
        model.addAttribute("thisPage",page);
        model.addAttribute("totalPage",totalPage);
        return "/jie/index";
    }

    @RequestMapping({"/dosearch"})
    public String doSearch(Model model, HttpServletRequest request, @RequestParam(value ="page",defaultValue ="0") int page, String keyword) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        List<Topic> mostPopularTopics=topicServiceImpl.queryTopicOrderByReplyNum().getResultData();
        model.addAttribute("user",user);
        model.addAttribute("mostPopularTopics",mostPopularTopics);
        if (user!=null){
            log.info(user.toString());
            model.addAttribute("loginResult",1);
        }
        else {
            log.info("未登录！");
            model.addAttribute("loginResult",-1);
        }
        if (page<0)
            page=0;
        String[] pages=new String[9];
        int totalPage=topicServiceImpl.countTopicByKeyword(keyword,page)/10+1;
        List<Topic> topics=topicServiceImpl.queryTopicByKeyword(keyword,page,10).getResultData();//page从0开始
        model.addAttribute("pageResult",PageResult.createPage(totalPage,page));
        model.addAttribute("topics",topics);
        model.addAttribute("thisPage",page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("thiskeyword",keyword);
        return "/jie/listSearch";
    }


    @RequestMapping({"/toDetail"})
    public String toTopicDetail(Model model, HttpServletRequest request, @RequestParam(value ="id") int id,@RequestParam(value ="page",defaultValue ="0") int page){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        log.info("帖子详情");
        if (user!=null){
            log.info(user.toString());
            model.addAttribute("loginResult",1);
        }
        else {
            log.info("未登录！");
            model.addAttribute("loginResult",-1);
        }
        Topic topic =topicServiceImpl.queryTopicById(id).getResultData();
        if (topic==null){
            model.addAttribute("message","查询的帖子不存在");
            return "/error/404";
        }
        if (topic.getTopicState().compareToIgnoreCase("删除")==0){
            model.addAttribute("message","帖子涉嫌违规，已被删除");
            return "/error/404";
        }
        if (page<0)
            page=0;
        List<Reply> replies=topicServiceImpl.queryReplyByTopicId(topic.getId(),page).getResultData();//查询帖子的回复
        System.out.println(replies);
        int totalPage=(topicServiceImpl.countReplyByTopicId(topic.getId())-1)/10+1;
        List<Topic> mostPopularTopics=topicServiceImpl.queryTopicOrderByReplyNum().getResultData();//查询热议榜
        Topic topic2=new Topic();
        topic2.setId(topic.getId());
        topic2.setReadNum(topic.getReadNum()+1);
        ResultDomain resultDomain=topicServiceImpl.updateTopic(topic2);
        if (resultDomain.getResultCode()>0){
            topic.setReadNum(topic.getReadNum()+1);
        }

        List<Collect> collect=null;
        int hasCollect=-1;
        if (user!=null)
            collect=topicServiceImpl.queryCollect(topic.getId(),user.getId()).getResultData();
        if (collect!=null){
            if (collect.size()==0)
                hasCollect=-1;
            else
                hasCollect=1;
        }
        System.out.println(hasCollect);
        model.addAttribute("pageResult",PageResult.createPage(totalPage,page));
        model.addAttribute("thisPage",page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("hasCollect",hasCollect);//帖子已被当前用户收藏
        model.addAttribute("user",user);
        model.addAttribute("mostPopularTopics",mostPopularTopics);
        model.addAttribute("topic",topic);
        model.addAttribute("replies",replies);
        return "/jie/detail";

    }

    /**
     * 更新帖子信息
     * @param model
     * @param topic
     * @return
     */
    @PostMapping({"/topicInfoUpload"})
    @ResponseBody
    public ResultDomain topicInfoUpload(Model model,@RequestBody Topic topic){
        log.info(topic.toString());
        ResultDomain resultDomain=topicServiceImpl.updateTopic(topic);
        if (resultDomain.getResultCode()>0){//上传成功
            if (topic.getTop()!=null){
                if (topic.getTop()==1){
                    resultDomain.setResultMsg("置顶成功");
                }
                else
                    resultDomain.setResultMsg("取消置顶成功");
            }
           if (topic.getEssence()!=null){
               if (topic.getEssence()==1){
                   resultDomain.setResultMsg("已加精");
               }
               else
                   resultDomain.setResultMsg("已取消加精");
           }
           if (topic.getTopicState()!=null){
               if (topic.getTopicState().compareToIgnoreCase("删除")==0){
                   resultDomain.setResultMsg("帖子已删除");
                   resultDomain.setResultCode(2);//2表示帖子被删除
               }
           }
        }
        return resultDomain;
    }

    @PostMapping({"/deleteReply"})
    @ResponseBody
    public ResultDomain deleteReply(Model model, HttpServletRequest request,@RequestParam(value = "id") int id,@RequestParam(value ="topicId") int topicId){
        ResultDomain resultDomain=topicServiceImpl.deleteReply(id);
        if (resultDomain.getResultCode()>0){
            Topic topic=new Topic();
            topic.setId(topicId);
            topic.setReplyNum(topicServiceImpl.countReplyByTopicId(topic.getId()));
            topicServiceImpl.updateTopic(topic);
        }
        return resultDomain;

    }

    @PostMapping({"/collectTopic"})
    @ResponseBody
    public ResultDomain collectTopic(Model model, HttpServletRequest request,@RequestBody Collect collect){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        collect.setCollectTime(new Date());
        ResultDomain resultDomain=new ResultDomain();
        if (user==null){
            resultDomain.setResultMsg("用户尚未登录请登陆后重试！");
            resultDomain.setResultCode(-1);
            return resultDomain;
        }
        if(topicServiceImpl.queryCollect(collect.getTopicId(),collect.getUserId()).getResultData().size()==0)
            resultDomain=topicServiceImpl.addCollect(collect);
        else{
            resultDomain.setResultMsg("收藏失败，帖子已被当前用户收藏！");
            resultDomain.setResultCode(-1);
        }
        return resultDomain;
    }

    @PostMapping({"/cancelCollectTopic"})
    @ResponseBody
    public ResultDomain cancelCollectTopic(Model model, HttpServletRequest request,@RequestBody Collect collect){
        collect.setCollectTime(new Date());
        ResultDomain resultDomain=topicServiceImpl.deleteCollect(collect.getTopicId(),collect.getUserId());
        return resultDomain;
    }

}
