package edu.devernul.project.controller;

import edu.devernul.project.dao.AbstractDaoStatus;
import edu.devernul.project.dao.AbstractDaoTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import edu.devernul.project.model.Status;
import edu.devernul.project.model.Task;
import edu.devernul.project.validation.TaskValidator;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;


@Controller
public class TasksController {

    private static final Logger logger = LoggerFactory.getLogger(TasksController.class);
    public static final int TASK_ON_PAGE = 5;
    public static final int INDEX_FIRST_PAGE = 1;

    @Autowired
    @Qualifier(value = "taskDao")
    private AbstractDaoTask taskDao;

    @Autowired
    @Qualifier(value = "statusDao")
    private AbstractDaoStatus statusDao;

    @Autowired
    @Qualifier(value = "taskValidator")
    private TaskValidator taskValidator;

    @ModelAttribute("statuses")
    public List<Status> getAllProjects() {
        if(statusDao.findAll().isEmpty()){

            statusDao.create(new Status("New"));
            statusDao.create(new Status("Process"));
            statusDao.create(new Status("Complete"));

        }
        return statusDao.findAll();
    }

    @ModelAttribute("tasks")
    public List<Task> getAllTasks() {
        //return taskDao.findAll();
        return taskDao.findPage(0, TASK_ON_PAGE);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public String get(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("page_id", INDEX_FIRST_PAGE);
        model.addAttribute("countsTasks", taskDao.size());
        return "tasks";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/tasks/filter/{statusId}/")
    public ModelAndView get(@PathVariable String statusId,Model model) {
        Status status = (Status) statusDao.getById(Integer.parseInt(statusId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks",taskDao.findAllbyStatus(status));
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("statusName",status.getName());
        modelAndView.setViewName("tasks");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/page_id/")
    public String redirToNullPage(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("page_id", INDEX_FIRST_PAGE);
        model.addAttribute("countsTasks", taskDao.size());
        return "tasks";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/tasks/page_id/{pageId}/")
    public ModelAndView get(@PathVariable Integer pageId,Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks",taskDao.findPage(TASK_ON_PAGE *pageId - TASK_ON_PAGE, TASK_ON_PAGE *pageId));
        modelAndView.addObject("task", new Task());
        modelAndView.addObject("countsTasks", taskDao.size());
        modelAndView.addObject("page_id", pageId);
        modelAndView.setViewName("tasks");
        return modelAndView;
    }


    @RequestMapping(method = RequestMethod.POST)
    public String add( @ModelAttribute("task") @Valid Task  task, BindingResult result,Model model) {
        //taskValidator.validate(task, result);
        if (result.hasErrors()){
            model.addAttribute("countsTasks", taskDao.size());
            model.addAttribute("page_id", INDEX_FIRST_PAGE);
            return "tasks";
        }


        taskDao.create(task);
        return "redirect:/tasks";
    }


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Status.class, "status", new PropertyEditorSupport() {

            public String getAsText() {
                Object value = getValue();
                if (value != null) {
                    Status status = (Status) value;
                    return status.getName();
                }
                return null;
            }

            public void setAsText(String text) {
                if (text instanceof String) {
                    Integer statusId = Integer.parseInt(text);
                    Status status = (Status) statusDao.getById(statusId);
                    setValue(status);
                }
            }
        });


    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{action}/{id}")
    public String handleAction(@PathVariable Integer id, @PathVariable String action, Model model) {
        Task task = (Task) taskDao.getById(id);
        if (action.equalsIgnoreCase("edit")) {
            model.addAttribute("task", task);
            model.addAttribute("statuses", statusDao.findAll());
            model.addAttribute("countsTasks", taskDao.size());
            model.addAttribute("page_id", 1);
            return "tasks";
        } else if (action.equalsIgnoreCase("delete")) {
            taskDao.delete(task);
        }
        else if (action.equalsIgnoreCase("begin")) {
            taskDao.updateStatus(task, (Status) statusDao.getByName("Process"));
        }
        else if (action.equalsIgnoreCase("complete")) {
            taskDao.updateStatus(task, (Status) statusDao.getByName("Complete"));
        }
        logger.debug("actions   {} !!!",action);
        return "redirect:/tasks";
    }


}
