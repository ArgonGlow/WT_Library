package main.WTLibraryApp.Book.Label;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LabelController {

	@Autowired
	private LabelService labelService;
	
	@GetMapping("/label")
	public String addLabel(@ModelAttribute Label label, Model model) {
		model.addAttribute("labels", labelService.allLabels());
		return "books/label";
	}
	
	@PostMapping("/label")
	public String Editlabel(@ModelAttribute Label label) {
		labelService.updateLabel(label);
		return "redirect:/label";
	}
	
	@PostMapping("/label/delete")
	public String RemoveLabel(@ModelAttribute Label label) {
		labelService.deleteLabel(label);
		return "redirect:/label";
	}
	
	@PostMapping("/label/add")
	public String AddLabel(@ModelAttribute Label label) {
		labelService.addLabel(label);
		return "redirect:/label";
	}
}
