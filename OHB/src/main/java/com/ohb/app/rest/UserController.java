package com.ohb.app.rest;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;
import com.ohb.app.util.api.APIName;
import com.ohb.app.util.api.APIStatus;
import com.ohb.app.util.api.APIUtil;
import com.ohb.app.util.api.Constant;
import com.ohb.app.util.api.DateUtil;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohb.app.model.User;
import com.ohb.app.model.UserToken;
import com.ohb.app.repo.UserRepository;
import com.ohb.app.service.MailService;
import com.ohb.app.service.SMSServive;
import com.ohb.app.service.UserService;
import com.ohb.app.service.UserTokenService;
import com.ohb.app.util.MD5Hash;
import com.ohb.app.util.OhbUtil;
import com.ohb.app.util.UniqueID;
import com.ohb.app.api.response.StatusResponse;

@RestController
@RequestMapping(APIName.USERS)
public class UserController extends APIUtil{

	@Autowired
    private UserService userService;
    @Autowired
    private UserTokenService userTokenService;
    
    @Autowired
	private MailService mailService;

    @RequestMapping(path = APIName.USERS_REGISTER, method = RequestMethod.POST, produces = APIName.CHARSET)
    public String register(
            @RequestBody User user
    ) {

        // check user already exists
        User existed = userService.getUserByEmail(user.getEmail(), user.getUsername());
        if (existed == null) {
            // email is valid to create user
            String email = user.getEmail(),
                    password = user.getPassword();
            
            if (email != null && !email.equals("") && password != null && !password.equals("")) {

                Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email);

                if (!matcher.matches() || password.length() < 6) {
                    statusResponse = new StatusResponse(APIStatus.ERR_INVALID_DATA);
                    return writeObjectToJson(statusResponse);
                }
                String uuid = UUID.randomUUID().toString();

                User userSignUp = new User();
                userSignUp.setUser_id(UniqueID.getUUID());
            
                userSignUp.setCreateDate(new Date());
                userSignUp.setEmail(email);
                userSignUp.setUsername(user.getUsername());
                userSignUp.setPhone(user.getPhone());
                userSignUp.setAddress(user.getAddress());
                userSignUp.setFirstName(user.getFirstName());
                userSignUp.setLastName(user.getLastName());

                try {
                    userSignUp.setPassword(MD5Hash.MD5Encrypt(password + userSignUp.getUser_id()));
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException("Encrypt user password error", ex);
                }

//                userSignUp.setRoleId(Constant.USER_ROLE.REGISTED_USER.getRoleId());
                userSignUp.setStatus(Constant.USER_STATUS.ACTIVE.getStatus());
                userSignUp.setToken(uuid);
                userService.save(userSignUp);
                if (userSignUp != null) {
                	char[] otp=SMSServive.generateOTP(4);
                	String output="";
                	for(char c:otp){
                		output+=c+" ";
                	}
                	String otpbody="Dear "+userSignUp.getFirstName()+userSignUp.getLastName()+", Welcome to OHB,"
                			+ "your activation OTP is " +output+" . Treat this as confidential,please do not share it";
                	if(userSignUp.getPhone()!=null && userSignUp.getPhone()!=""){
                		String phone=userSignUp.getPhone();
                	SMSServive.sendMessage(otpbody,phone);
                	
                	
                	}
                	mailService.sendNewRegistration(user.getEmail(), userSignUp.getToken(), userSignUp);
                }
                
                statusResponse = new StatusResponse(APIStatus.OK.getCode(), userSignUp);
            } else {
                statusResponse = new StatusResponse(APIStatus.ERR_INVALID_DATA);
                return writeObjectToJson(statusResponse);
            }

        } else {
            // notify user already exists
            statusResponse = new StatusResponse(APIStatus.USER_ALREADY_EXIST);
        }

        return writeObjectToJson(statusResponse);
    }
    
    @RequestMapping(value = APIName.USERS_LOGIN, method = RequestMethod.POST, produces = APIName.CHARSET)
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(name="keepMeLogin",required = false) Boolean keepMeLogin
    ) {

        if ("".equals(email) || "".equals(password)) {
            // invalid paramaters
            statusResponse = new StatusResponse(APIStatus.INVALID_PARAMETER);
        } else {
            User userLogin = userService.getUserByEmail(email);

            if (userLogin != null) {
                String passwordHash = null;
                try {
                    passwordHash = MD5Hash.MD5Encrypt(password + userLogin.getUser_id());
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException("User login encrypt password error", ex);
                }

                int userStatus = userLogin.getStatus();
                if (userStatus == Constant.USER_STATUS.ACTIVE.getStatus()) {
                    if (passwordHash.equals(userLogin.getPassword())) {
                        UserToken userToken = new UserToken();
                        userToken.setToken(UniqueID.getUUID());
                        userToken.setUserId(userLogin.getUser_id());

                        Date currentDate = new Date();
                        userToken.setLoginDate(DateUtil.convertToUTC(currentDate));

                        Date expirationDate = keepMeLogin ? new Date(currentDate.getTime() + Constant.DEFAULT_REMEMBER_LOGIN_MILISECONDS) : new Date(currentDate.getTime() + Constant.DEFAULT_SESSION_TIME_OUT);
                        userToken.setExpirationDate(DateUtil.convertToUTC(expirationDate));

                        userTokenService.save(userToken);
                        statusResponse = new StatusResponse<>(HttpStatus.OK.value(), userToken);
                    } else {
                        // wrong password
                        statusResponse = new StatusResponse(APIStatus.ERR_USER_NOT_VALID);
                    }
                } else if (userStatus == Constant.USER_STATUS.PENDING.getStatus()) {
                    statusResponse = new StatusResponse(APIStatus.USER_PENDING_STATUS);
                } else {
                    statusResponse = new StatusResponse(APIStatus.ERR_USER_NOT_VALID);
                }
            } else {
                // can't find user by email address in database
                statusResponse = new StatusResponse(APIStatus.ERR_USER_NOT_EXIST);
            }
        }

        return writeObjectToJson(statusResponse);
    }
    /*
	

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String listUsers(Model model) throws Throwable {
		List<User> users=repository.findAll();
		model.addAttribute("userlist", users);
		return OhbUtil.convertToJSONWithoutNull(model);
	}
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String editUser(@PathVariable("userId") int userId, @RequestBody User user,Model model)throws Throwable{
		User currentUser=repository.findOne(userId);
		String msg="";
		if(currentUser==null){
			msg="No record found on given id";
			model.addAttribute("message", msg);
			return OhbUtil.convertToJSONWithoutNull(model);
		}
		if(user.getUsername()!=null && !user.getUsername().isEmpty()){
		currentUser.setUsername(user.getUsername());
		}
		if(user.getAddress()!=null && !user.getAddress().isEmpty()){
		currentUser.setAddress(user.getAddress());
		}
		if(user.getFirstName()!=null && !user.getFirstName().isEmpty()){
		currentUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName()!=null && !user.getLastName().isEmpty()){
		currentUser.setLastName(user.getLastName());
		}
		if(user.getEmail()!=null && !user.getEmail().isEmpty()){
			currentUser.setEmail(user.getEmail());
			}
		if(user.getPhone()!=null && !user.getPhone().isEmpty()){
		currentUser.setPhone(user.getPhone());
		}
		if(user.getPassword()!=null  && !user.getPassword().isEmpty()){
		currentUser.setPassword(user.getPassword());
		}
		currentUser=repository.saveAndFlush(currentUser);
		msg="User Update Succefully";
		model.addAttribute("msg", msg);
		model.addAttribute("user", currentUser);
		return OhbUtil.convertToJSONWithoutNull(model);
	}*/
}
