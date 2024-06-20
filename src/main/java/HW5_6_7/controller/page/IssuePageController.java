package HW5_6_7.controller.page;

import HW5_6_7.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(path = "/page/issue")
public class IssuePageController {

    private final String issuePage = "issues";
    private final String issueTableName = "List of issues";
    @Autowired
    private IssueService issueService;

    @GetMapping(path = "/all")
    public String getAllIssues(Model model) {
        Map<String, Object> attribute = Map.of(
                "issues", issueService.getAll(),
                "header", issueTableName
        );
        model.addAllAttributes(attribute);
        return issuePage;
    }
}
