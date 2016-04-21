package com.eshop.controller.servlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.patchca.color.ColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

public class IdentifyCodeServlet extends HttpServlet{

	/*@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        cs.setWidth(100);
        cs.setHeight(40);
        FileOutputStream fos = new FileOutputStream("patcha_demo.png");
        EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
        fos.close();
        
		// flush it in the response
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		ImageIO.write(cs.getCaptcha().getImage(), "jpeg", responseOutputStream);
		responseOutputStream.flush();
		responseOutputStream.close();
	}*/
	
	private static final long serialVersionUID = 3796351198097771007L; 
	private static ConfigurableCaptchaService cs = null;
	private static ColorFactory cf = null;
	private static RandomWordFactory wf = null;
	private static Random r = new Random();
	private static CurvesRippleFilterFactory crff = null;
	private static MarbleRippleFilterFactory mrff = null;
	private static DoubleRippleFilterFactory drff = null;
	private static WobbleRippleFilterFactory wrff = null;
	private static DiffuseRippleFilterFactory dirff = null;

	@Override
	public void init() throws ServletException {
		super.init();
		cs = new ConfigurableCaptchaService();
		cf = new SingleColorFactory(new Color(25, 60, 170));
		wf = new RandomWordFactory();
		crff = new CurvesRippleFilterFactory(cs.getColorFactory());
		drff = new DoubleRippleFilterFactory();
		wrff = new WobbleRippleFilterFactory();
		dirff = new DiffuseRippleFilterFactory();
		mrff = new MarbleRippleFilterFactory();
		cs.setWordFactory(wf);
		cs.setColorFactory(cf);
		cs.setWidth(120);
		cs.setHeight(50);
	}

	@Override
	public void destroy() {
		wf = null;
		cf = null;
		cs = null;
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
		response.setHeader("cache", "no-cache");
		wf.setMaxLength(4);
		wf.setMinLength(4);
		HttpSession session = request.getSession(true);
		OutputStream os = response.getOutputStream();
		switch (r.nextInt(5)) {
		case 0:
			cs.setFilterFactory(crff);
			break;
		case 1:
			cs.setFilterFactory(mrff);
			break;
		case 2:
			cs.setFilterFactory(drff);
			break;
		case 3:
			cs.setFilterFactory(wrff);
			break;
		case 4:
			cs.setFilterFactory(dirff);
			break;
		}
		String captcha = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
		session.setAttribute("identifyCode", captcha);//System.out.println(captcha);
		os.flush();
		os.close();
	}
	
}
