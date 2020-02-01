package com.test.hci;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.hci.model.Module;
import com.test.hci.model.User;
import com.test.hci.model.UserModule;
import com.test.hci.service.ModuleService;
import com.test.hci.service.UserService;
import com.test.hci.tuple.UserModuleList;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class ApplicationTest {
    private static ObjectMapper objectMapper;
    private static RestTemplate restTemplate;
    private static User userA, userB, userC;
    private static Module promoCard, categoryCard, flashSaleCard, historyCard, newsCard;
    private static List<UserModule> userModuleAList = new LinkedList<>();
    private static List<UserModule> userModuleBList = new LinkedList<>();
    private static List<UserModule> userModuleCList = new LinkedList<>();

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserService userService;

    @BeforeAll
    public static void setupTest() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();

        userA = new User("UserA");
        userB = new User("UserB");
        userC = new User("UserC");

        promoCard = new Module("PromoCard");
        categoryCard = new Module("CategoryCard");
        flashSaleCard = new Module("FlashSaleCard");
        historyCard = new Module("HistoryCard");
        newsCard = new Module("NewsCard");

        userModuleAList.add(new UserModule(userA, promoCard, 1));
        userModuleAList.add(new UserModule(userA, categoryCard, 2));
        userModuleAList.add(new UserModule(userA, flashSaleCard, 3));
        userModuleAList.add(new UserModule(userA, historyCard, 4));
        userModuleAList.add(new UserModule(userA, newsCard, 5));

        userModuleBList.add(new UserModule(userB, promoCard, 1));
        userModuleBList.add(new UserModule(userB, newsCard, 2));
        userModuleBList.add(new UserModule(userB, flashSaleCard, 3));
        userModuleBList.add(new UserModule(userB, categoryCard, 4));
        userModuleBList.add(new UserModule(userB, newsCard, 5));

        userModuleCList.add(new UserModule(userC, promoCard, 1));
        userModuleCList.add(new UserModule(userC, flashSaleCard, 2));
        userModuleCList.add(new UserModule(userC, categoryCard, 3));
        userModuleCList.add(new UserModule(userC, newsCard, 4));
        userModuleCList.add(new UserModule(userC, historyCard, 5));
    }

    @Test
    public void testInsertUsers() {
        User[] users = new User[] { userA, userB, userC };
        userService.insertUsers(users);
        for (User user : users) {
            User existingUser = userService.selectOneUserById(user.getId());
            Assert.assertNotNull("User " + user.getId() + " not found", existingUser);
        }
    }

    @Test
    public void testInsertModules() {
        Module[] modules = new Module[] {
                promoCard,
                categoryCard,
                flashSaleCard,
                historyCard,
                newsCard
        };
        moduleService.insertModules(modules);
        for (Module module : modules) {
            Module existingModule = moduleService.selectOneModuleByName(module.getName());
            Assert.assertNotNull("Module " + module.getName() + " not found", existingModule);
        }
    }

    @Test
    public void testInsertUserModules() {
        List<UserModule> allUserModules = new LinkedList<>();
        allUserModules.addAll(userModuleAList);
        allUserModules.addAll(userModuleBList);
        allUserModules.addAll(userModuleCList);

        UserModule[] userModules = allUserModules.stream().toArray(n -> new UserModule[n]);
        userService.insertUserModules(userModules);
        for (UserModule userModule : userModules) {
            UserModule existingUserModule = userService.selectOneUserModule(userModule);
            Assert.assertNotNull("User " + userModule.getUser().getId() + " with module " + userModule.getModule().getName() + " and module order " + userModule.getModuleOrder() + " not found", existingUserModule);
        }
    }

    @Test
    public void testGetUserAModules() {
        testGetUserModules(userA, userModuleAList);
    }

    @Test
    public void testGetUserBModules() {
        testGetUserModules(userB, userModuleBList);
    }

    @Test
    public void testGetUserCModules() {
        testGetUserModules(userC, userModuleCList);
    }

    @Test
    public void testGetUserAModulesViaRest() throws JsonProcessingException {
        testGetUserModulesViaRest(userA, userModuleAList);
    }

    @Test
    public void testGetUserBModulesViaRest() throws JsonProcessingException {
        testGetUserModulesViaRest(userB, userModuleBList);
    }

    @Test
    public void testGetUserCModulesViaRest() throws JsonProcessingException {
        testGetUserModulesViaRest(userC, userModuleCList);
    }

    private void testGetUserModules(User user, List<UserModule> userModuleList) {
        List<UserModule> moduleList = userService.selectAllUserModules(user);
        Assert.assertNotNull("ModuleList is null", moduleList);
        String expected = toStringUserModules(userModuleList);
        String found = toStringUserModules(moduleList);
        Assert.assertEquals("Expected: " + expected + "\nFound: " + found, expected, found);
        System.out.println("Expected matched found: " + expected);
    }

    private void testGetUserModulesViaRest(User user, List<UserModule> userModelModuleList) throws JsonProcessingException {
        UserModuleList restResult = restTemplate.getForObject(
                "http://localhost:8181/user/get-module/" + user.getId(),
                UserModuleList.class
        );
        UserModuleList userModuleList = new UserModuleList();
        userModuleList.setModelModuleList(userModelModuleList);
        String expected = toStringUserModules(userModuleList);
        String found = toStringUserModules(restResult);
        Assert.assertEquals("Expected: " + expected + "\nFound: " + found, expected, found);
        System.out.println("Expected matched found: " + expected);
    }

    private String toStringUserModules(UserModuleList userModuleList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userModuleList);
    }

    private String toStringUserModules(List<UserModule> userModuleList) {
        StringBuffer sb = new StringBuffer();
        if (userModuleList != null && userModuleList.size() > 0) {
            sb.append(userModuleList.get(0).getUser().getId());
            sb.append(" : ");
            for (UserModule userModule : userModuleList) {
                sb.append(userModule.getModule().getName());
                sb.append(", ");
            }
            sb.delete(sb.lastIndexOf(", "), sb.length());
        }
        return sb.toString();
    }
}
