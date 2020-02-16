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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/topicManage")
public class TopicController {
    @Autowired
    private TopicServiceImpl topicServiceImpl;
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

    @PostMapping (value="/deleteFile")
    @ResponseBody//json交互注解
    public Map<String, Object> deleteFile(Model model, HttpServletRequest request ) {
        log.info("删除未发布的帖子上传的图片");//用户未保存就刷新或关闭页面
        File files=new File(path);
        log.info(path);
        try{
            if(files.getParentFile().exists()){
                deleteAllFile(files.getParentFile());
            }
            else {
                log.info("删除失败,文件不存在!");
                Map<String,Object> map=new HashMap<>();
                map.put("code",-1);
                map.put("msg"," 删除失败,文件不存在！");
                return map;
            }
        }catch (Exception e){
            log.info("删除失败：",e.getMessage());
            Map<String,Object> map=new HashMap<>();
            map.put("code",-1);
            map.put("msg"," 删除失败！");
            return map;
        }
        log.info("删除成功");
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","删除成功！");
        return map;
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
                String filepath = "C:\\Users\\1\\IdeaProjects\\bbs\\topicImg\\" + dateStr+"\\"+uuid+"." + prefix;
                path=filepath;
                File files=new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
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
    public ResultDomain saveTopic(Model model, HttpServletRequest request, Topic topic) {
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
            return resultDomain=topicServiceImpl.addTopic(topic);

        }
        resultDomain.setResultMsg("非法操作！请登录后重试！");
        resultDomain.setResultCode(-1);
        return resultDomain;
    }

    @RequestMapping({"/column"})
    @ResponseBody
    public ResultDomain getAllTopic(Model model, HttpServletRequest request,int page,int moduleId) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        ResultDomain resultDomain=new ResultDomain();
        resultDomain.setResultMsg("非法操作！请登录后重试！");
        resultDomain.setResultCode(-1);
        return resultDomain;
    }

}
