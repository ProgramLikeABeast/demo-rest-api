package com.example.springbootrest.rest;

import com.example.springbootrest.entity.*;
import com.example.springbootrest.service.*;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class DataRestController {
    // expose "/user" and return a list of Users
    private UserService userService;
    private StrategyService strategyService;
    private WidgetService widgetService;
    private ProductService productService;
    private SmallImageService smallImageService;
    private CategoryService categoryService;
    private OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(DataRestController.class);
    // inject user dao
    @Autowired
    public  DataRestController(
            UserService theUserService,
            StrategyService theStrategyService,
            WidgetService theWidgetService,
            ProductService theProductService,
            SmallImageService theSmallImageService,
            CategoryService theCategoryService,
            OrderService theOrderService
            ) {
        userService = theUserService;
        strategyService = theStrategyService;
        widgetService = theWidgetService;
        productService = theProductService;
        smallImageService = theSmallImageService;
        categoryService = theCategoryService;
        orderService = theOrderService;
    }


    @GetMapping("/")
    public String welcome() {
        return "Hello World! <a href=\"/login\">Login</a> <a href=\"/logout\">Logout</a> <a href=\"/user_profile\">user</a>";
    }

    // select all
    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    // select one
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User theUser = userService.findById(userId);

        if(theUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return theUser;
    }

    @GetMapping("/users/byEmail/{userEmail}")
    public User getUser(@PathVariable String userEmail) {
        return userService.findByEmail(userEmail);
    }

    // create new
    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        // in case they pass an id on JSON ... set id to 0
        // is to force a save of a new item ... instead of update
        theUser.setId(0);
        return userService.save(theUser);
    }

    // Update one
    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {

        return userService.save(theUser);
    }

    // Delete one
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {

        User tempUser = userService.findById(userId);
        if(tempUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        userService.deleteById(userId);
        return "Deleted user id - " + userId;
    }

    // Delete all
    @DeleteMapping("/users")
    public String deleteAllUsers() {
        userService.deleteAll();
        return "Deleted all users.";
    }

    @GetMapping("/users/{email}/{password}")
    public boolean loginAttempt(@PathVariable String email, @PathVariable String password) {
        return userService.checkUserExistence(email, password);
    }
    // Strategy Section

    // select one
    // This method saves the file data to a local file
    /*@GetMapping("/strategies/{theId}")
    public ResponseEntity<String> saveStrategiesLocally(@PathVariable int theId) {
        Strategy fileEntity = strategyService.findById(theId);

        if (fileEntity != null) {
            try {
                String filePath = "C:\\Users\\xuyan\\OneDrive\\Desktop\\receive folder\\" + fileEntity.getFilename();
                FileOutputStream fos = new FileOutputStream(filePath);
                fos.write(fileEntity.getFileData());
                fos.close();
                return ResponseEntity.ok("File saved locally: " + filePath);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the file locally");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }*/

    @GetMapping("/strategies/{theId}")
    public ResponseEntity<byte []> getStrategyFile(@PathVariable int theId) {
        Strategy theStrategy = strategyService.findById(theId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("file-name", theStrategy.getFilename());
        headers.add("content-type", theStrategy.getContentType());

        return ResponseEntity.ok()
                .headers(headers)
                .body(theStrategy.getFileData());
    }

    @GetMapping("/strategies/getJson/{theId}")
    public Strategy saveStrategyJsonLocally(@PathVariable int theId) {
        Strategy theStrategy = strategyService.findById(theId);
        return theStrategy;
    }

    // upload one
    @PostMapping(value="/strategies", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadStrategy(@RequestParam(value="any_file", required=false) MultipartFile file) {
        try {
            Strategy theStrategyFile = strategyService.saveFile(file);
            return ResponseEntity.ok("File uploaded successfully. File ID: " + theStrategyFile.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
        }
    }

    @DeleteMapping("/strategies")
    public String deleteAllStrategies() {
        strategyService.deleteAll();
        return "Deleted all strategies.";
    }






    // widget endpoints

    @GetMapping("/widgets/count")
    public long getWidgetCount() {
        return widgetService.getcount();
    }

    // load a widget
    @GetMapping("/widgets/{theId}")
    public WidgetInfo getWidget(@PathVariable int theId) {
        Widget theWidget = widgetService.findById(theId);

        return new WidgetInfo(theWidget.getWidget_name(),
                          theWidget.getText_description(),
                          theWidget.getBg_color()
                          );
    }

    // load a widget pict
    @GetMapping("/widgets/pict/{theId}")
    public ResponseEntity<byte []> getWidgetPict(@PathVariable int theId) {
        Widget theWidget = widgetService.findById(theId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("file-name", theWidget.getLarge_image_filename());
        headers.add("content-type", theWidget.getLarge_image_content_type());

        return ResponseEntity.ok()
                .headers(headers)
                .body(theWidget.getLarge_image_data());
    }

    // upload a widget
    @PostMapping(value="/widgets", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadWidget(@RequestParam(value="avatar_image", required=false) MultipartFile avatar_image,
                                               @RequestParam(value="large_image", required=false) MultipartFile large_image,
                                               @RequestParam String widget_name,
                                               @RequestParam String description,
                                               @RequestParam String bg_color
                                               ) throws IOException {
        Widget theWidget = new Widget(
                widget_name,
                description,
                bg_color,
                avatar_image.getContentType(),
                avatar_image.getOriginalFilename(),
                avatar_image.getBytes(),
                large_image.getContentType(),
                large_image.getOriginalFilename(),
                large_image.getBytes()
                );
        try {
            widgetService.saveWidget(theWidget);
            return ResponseEntity.ok("Widget uploaded successfully. File ID: " + theWidget.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the widget");
        }
    }

    // Delete all widgets
    @DeleteMapping("/widgets")
    public String deleteAllWidgets() {
        widgetService.deleteAll();
        return "Deleted all widgets.";
    }






    // product endpoints

    @PostMapping(value="/products")
    public Product uploadProduct(@RequestBody Product theProduct) throws IOException {
        theProduct.setPid(0);
        return productService.saveProduct(theProduct);
    }

    @GetMapping ("/products/count")
    public long getProductCount(){
        return productService.getcount();
    }

    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable int productId) {
        Product theProduct = productService.findById(productId);

        if(theProduct == null) {
            throw new RuntimeException("User id not found - " + productId);
        }
        return theProduct;
    }

    @GetMapping("/products/byCategory/{cid}")
    public List<Product> findProductByCid(@PathVariable int cid){
        return productService.findByCategory(cid);
    }

    @GetMapping("/4products/byCategory/{cid}")
    public List<Product> findFirst4ProductByCid(@PathVariable int cid){
        return productService.find4ByCategory(cid);
    }

    @GetMapping("/products/byName/{productName}")
    public Product findProductByName(@PathVariable String productName){
        return productService.findByProductName(productName);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @DeleteMapping("/products")
    public String deleteAllProducts() {
        productService.deleteAll();
        return "Deleted all products.";
    }





    // small_image endpoints
    @GetMapping("/small_images/count")
    public long getSmallImageCount() {
        return smallImageService.getcount();
    }

    @GetMapping("/small_images/{theId}")
    public ResponseEntity<byte []> loadSmallImage(@PathVariable int theId) {
        SmallImage theImage = smallImageService.findById(theId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("file-name", theImage.getImage_name());
        headers.add("content-type", theImage.getContent_type());

        return ResponseEntity.ok()
                .headers(headers)
                .body(theImage.getImage_data());
    }

    @PostMapping(value="/small_images", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadSmallImage(
            @RequestParam(value="image", required=false) MultipartFile theImage,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="pid", required=true) Integer pid
    ) throws IOException {
        SmallImage retImage = new SmallImage(
                pid,
                name,
                theImage.getContentType(),
                theImage.getBytes()
        );
        try {
            smallImageService.saveImage(retImage);
            logger.info("******************************************");
            return ResponseEntity.ok("Small image uploaded successfully. File ID: " + retImage.getSiid());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the small image");
        }
    }

    @PostMapping(value="/small_images/limit64kb", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadSmallImageLimit64kb(
            @RequestParam(value="image", required=false) MultipartFile theImage,
            @RequestParam(value="name", required=true) String name,
            @RequestParam(value="pid", required=true) Integer pid
    ) throws IOException {
        logger.info("******************************************");
        try {
            final float maxFileSize = (float) 64 * 1024; // 64 KB
            BufferedImage originalImage = ImageIO.read(theImage.getInputStream());
            double scale = 1.0; // Start with original size
            byte[] imageBytes;
            boolean divided = false;

            logger.info("*********************" + scale + "*********************");

            do {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // Dynamically adjust scale to reduce file size
                BufferedImage scaledImage = Thumbnails.of(originalImage)
                        .scale(scale)
                        .asBufferedImage();

                ImageIO.write(scaledImage, "png", baos);
                imageBytes = baos.toByteArray();

                if(!divided && maxFileSize < imageBytes.length){
                    scale = maxFileSize / imageBytes.length;
                    divided = true;
                }
                scale *= 0.05; // Decrease scale by 5%
            } while (imageBytes.length > maxFileSize);

            logger.info("*********************" + imageBytes.length + "*********************");

            SmallImage retImage = new SmallImage(
                    pid,
                    theImage.getName(),
                    theImage.getContentType(),
                    imageBytes
            );

            smallImageService.saveImage(retImage);
            return ResponseEntity.ok("Small image uploaded successfully. File ID: " + retImage.getSiid());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the small image");
        }
    }

    @DeleteMapping("/small_images")
    public String deleteAllSmallImages() {
        smallImageService.deleteAll();
        return "Deleted all small images.";
    }

    // category endpoints
    @GetMapping("/categories")
    public List<Category> findAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{cid}")
    public Category getCategory(@PathVariable int cid) {
        Category theCategory = categoryService.findByCid(cid);

        if(theCategory == null) {
            throw new RuntimeException("Category id not found - " + theCategory);
        }
        return theCategory;
    }

    @GetMapping("/categories/root")
    public List<Category> getRootCategories() {
        List<Category> rootCategories = categoryService.findAllRootCategories();

        if(rootCategories == null) {
            throw new RuntimeException("root categories not found - ");
        }
        return rootCategories;
    }

    @GetMapping("/categories/root/{root}")
    public List<Category> getCategoriesByRoot(@PathVariable String root) {
        List<Category> theCategories = categoryService.findAllCategoriesByRoot(root);

        if(theCategories == null) {
            throw new RuntimeException("Category id not found from root " + root);
        }
        return theCategories;
    }

    // order endpoints
    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.findAll();
    }
    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order theOrder){
        theOrder.setOid(0);
        return orderService.save(theOrder);
    }
    @GetMapping("/orders/byOid/{oid}")
    public Order getOrderByOid(@PathVariable int oid){
        Order theOrder = orderService.findByOid(oid);

        if(theOrder == null) {
            throw new RuntimeException("Order id not found - " + oid);
        }
        return theOrder;
    }
    @GetMapping("/orders/byUid/{uid}")
    public List<Order> getOrderByUid(@PathVariable int uid){
        return orderService.findByUid(uid);
    }
    @GetMapping("/orders/queue")
    public List<Order> getTheQueue(){
        return orderService.findAllUnprocessedOrders(0);
    }
    @PostMapping("/orders/process/{oid}")
    public Order processOrder(@PathVariable int oid){
        Order theOrder = orderService.findByOid(oid);

        if(theOrder == null) {
            throw new RuntimeException("Order id not found - " + oid);
        }

        theOrder.setStatus(1);
        orderService.save(theOrder);

        return theOrder;
    }

    @DeleteMapping("/orders/{oid}")
    public String deleteOrder(@PathVariable int oid) {

        Order tempUser = orderService.findByOid(oid);
        if(tempUser == null) {
            throw new RuntimeException("Order id not found - " + oid);
        }

        orderService.deleteByOid(oid);
        return "Deleted order id-" + oid;
    }

    @DeleteMapping("/orders")
    public String deleteAllOrders() {
        orderService.deleteAll();
        return "Deleted all orders.";
    }
}
