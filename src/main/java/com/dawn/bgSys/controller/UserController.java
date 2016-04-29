package com.dawn.bgSys.controller;

import com.alibaba.fastjson.JSONObject;
import com.dawn.bgSys.common.Utils;
import com.dawn.bgSys.common.WebJsonUtils;
import com.dawn.bgSys.domain.User;
import com.dawn.bgSys.domain.UserType;
import com.dawn.bgSys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * User: zhouchaoyi
 * Date: 15-9-23
 */
@Controller
@RequestMapping("/userMgmt")
public class UserController extends BaseController {
    /**
     * 日志工具
     */
    final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;


    /**
     * 根据Id获取用户类型
     *
     * @param jsonStr
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryUserTypeById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryUserTypeById(@RequestBody String jsonStr) throws Exception {

        String typeId = WebJsonUtils.getStringValue(jsonStr, "typeId", true);

        JSONObject result = this.userService.queryUserTypeById(Long.parseLong(typeId));

        JSONObject json = new JSONObject();
        json.put("data", result);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/addUserType", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUserType(@RequestBody String jsonStr) throws Exception {
        String typeCode = WebJsonUtils.getStringValue(jsonStr, "code", true);
        String typeName = WebJsonUtils.getStringValue(jsonStr, "name", true);
        String remark = WebJsonUtils.getStringValue(jsonStr, "remark", false);
        String status = WebJsonUtils.getBooleanValue(jsonStr,"status");

        UserType userType = new UserType();
        userType.setTypeCode(typeCode);
        userType.setTypeName(typeName);
        userType.setStatus(Byte.valueOf(status));
        userType.setRemark(remark);

        int success = this.userService.insertUserType(userType);
        //System.out.println("success="+success);
        JSONObject json = new JSONObject();
        json.put("data", success);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/updateUserType", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUserType(@RequestBody String jsonStr) throws Exception {
        String typeId = WebJsonUtils.getStringValue(jsonStr, "id", true);
        String typeCode = WebJsonUtils.getStringValue(jsonStr, "code", true);
        String typeName = WebJsonUtils.getStringValue(jsonStr, "name", true);
        String remark = WebJsonUtils.getStringValue(jsonStr, "remark", false);
        String status = WebJsonUtils.getBooleanValue(jsonStr,"status");

        UserType userType = new UserType();
        userType.setTypeId(Long.valueOf(typeId));
        userType.setTypeCode(typeCode);
        userType.setTypeName(typeName);
        userType.setStatus(Byte.valueOf(status));
        userType.setRemark(remark);

        int success = this.userService.updateUserType(userType);
        //System.out.println("success="+success);
        JSONObject json = new JSONObject();
        json.put("data", success);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/deleteUserType", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUserType(@RequestBody String jsonStr) throws Exception {
        String ids = WebJsonUtils.getStringValue(jsonStr, "ids", true);
        String[] array=ids.split(",");
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, array);

        int success = this.userService.deleteUserType(list);

        JSONObject json = new JSONObject();
        json.put("data", success);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/listUserType", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String listUserType() throws Exception {
        List<UserType> result = this.userService.listUserType();
        JSONObject json = new JSONObject();
        json.put("data", result);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/listUserByType", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String listUserByType(@RequestBody String jsonStr) throws Exception {
        String userType = WebJsonUtils.getStringValue(jsonStr, "userType", true);
        int currentPage = WebJsonUtils.getIntValue(jsonStr, "currentPage", true);
        int pageSize = WebJsonUtils.getIntValue(jsonStr, "pageSize", true);
        String orderBy = WebJsonUtils.getStringValue(jsonStr, "orderBy", false);
        String searchStr = WebJsonUtils.getStringValue(jsonStr, "searchStr", false);
        if(StringUtils.length(orderBy)>0) {
            orderBy=Utils.transOrderByStr(orderBy);
        }
        JSONObject result = this.userService.listUserByType(userType,currentPage,pageSize,orderBy,searchStr);
        JSONObject json = new JSONObject();
        json.put("data", result);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/addUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addUser(@RequestBody String jsonStr) throws Exception {
        String loginName = WebJsonUtils.getStringValue(jsonStr, "loginName", true);
        String userName = WebJsonUtils.getStringValue(jsonStr, "userName", true);
        String loginPassword = WebJsonUtils.getStringValue(jsonStr, "loginPassword", true);
        String userType = WebJsonUtils.getStringValue(jsonStr, "userType", true);
        String sex = WebJsonUtils.getStringValue(jsonStr, "sex", true);
        String idCardType = WebJsonUtils.getStringValue(jsonStr, "idCardType", false);
        String idCard = WebJsonUtils.getStringValue(jsonStr, "idCard", false);
        String publicAccount = WebJsonUtils.getBooleanValue(jsonStr,"publicAccount");
        String status = WebJsonUtils.getBooleanValue(jsonStr,"status");

        User user = new User();
        user.setIdCard(idCard);
        user.setIdCardType(Long.valueOf(idCardType));
        user.setLoginName(loginName);
        user.setLoginPassword(loginPassword);
        user.setPublicAccount(Byte.valueOf(publicAccount));
        user.setSex(Long.valueOf(sex));
        user.setStatus(Byte.valueOf(status));
        user.setUserName(userName);
        user.setUserType(userType);
        user.setRegistedDate(new Date());

        int success = this.userService.insertUser(user);
        //System.out.println("success="+success);
        JSONObject json = new JSONObject();
        json.put("data", success);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/queryUserById", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryUserById(@RequestBody String jsonStr) throws Exception {

        String id = WebJsonUtils.getStringValue(jsonStr, "id", true);

        User result = this.userService.queryUserById(id);

        JSONObject json = new JSONObject();
        json.put("data", result);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/updateUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUser(@RequestBody String jsonStr) throws Exception {
        String userId = WebJsonUtils.getStringValue(jsonStr, "id", true);
        String loginName = WebJsonUtils.getStringValue(jsonStr, "loginName", true);
        String userName = WebJsonUtils.getStringValue(jsonStr, "userName", true);
        String loginPassword = WebJsonUtils.getStringValue(jsonStr, "loginPassword", true);
        String userType = WebJsonUtils.getStringValue(jsonStr, "userType", true);
        String sex = WebJsonUtils.getStringValue(jsonStr, "sex", true);
        String idCardType = WebJsonUtils.getStringValue(jsonStr, "idCardType", false);
        String idCard = WebJsonUtils.getStringValue(jsonStr, "idCard", false);
        String publicAccount = WebJsonUtils.getBooleanValue(jsonStr,"publicAccount");
        String status = WebJsonUtils.getBooleanValue(jsonStr,"status");

        User user = new User();
        user.setUserId(userId);
        user.setIdCard(idCard);
        user.setIdCardType(Long.valueOf(idCardType));
        user.setLoginName(loginName);
        user.setLoginPassword(loginPassword);
        user.setPublicAccount(Byte.valueOf(publicAccount));
        user.setSex(Long.valueOf(sex));
        user.setStatus(Byte.valueOf(status));
        user.setUserName(userName);
        user.setUserType(userType);
        user.setRegistedDate(new Date());

        int success = this.userService.updateUser(user);
        //System.out.println("success="+success);
        JSONObject json = new JSONObject();
        json.put("data", success);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }

    @RequestMapping(value = "/deleteUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUser(@RequestBody String jsonStr) throws Exception {
        String ids = WebJsonUtils.getStringValue(jsonStr, "ids", true);
        String[] array=ids.split(",");
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, array);

        int success = this.userService.deleteUser(list);

        JSONObject json = new JSONObject();
        json.put("data", success);
        json.put("status", Utils.getSubStatus("获取数据成功！"));
        return json.toString();
    }


}