package edu.devernul.project.pdf;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.devernul.project.model.Task;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class PDFBuilder extends AbstractPdfView {


	public static final String TASK_ID = "TASK_ID";
	public static final String TASK_NAME = "TASK_NAME";
	public static final String TASK_DATE = "TASK_DATE";
	public static final String TASK_DESCRIPTION = "TASK_DESCRIPTION";
	public static final String TASK_STATUS = "TASK_STATUS";

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {



		List<Task> listTask = (List<Task>) model.get("tasks");


		for(Task task : listTask){
			doc.add(new Paragraph(TASK_ID + ":" +" "+task.getTaskId()));
			doc.add(new Paragraph(TASK_NAME + ":" +" "+task.getName()));
			doc.add(new Paragraph(TASK_DATE + ":" +" "+task.getDate().toString()));
			doc.add(new Paragraph(TASK_DESCRIPTION + ":" +" "+task.getDescription()));
			doc.add(new Paragraph(TASK_STATUS + ":" +" "+task.getStatus().getName().toString()));
			doc.add(new Paragraph("........"));
		}
	}

}