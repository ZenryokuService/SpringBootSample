@Grab("thymeleaf-spring5")

@RestController
class App {

   @RequestMapping("/")
   @ResponseBody
   def home(ModelAndView mav) {
      mav.setViewName("home")
      mav.addObject("msg", "Hello! This is a pen")
      mav
   }
}
