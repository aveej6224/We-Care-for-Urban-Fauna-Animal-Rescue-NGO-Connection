package action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.*;

import Beans.AcceptUser;
import Beans.ApproveList;
import Beans.Country;
import Beans.Organization;
import Beans.User;
import Beans.ViewStatus;
import Constants.Constants;
import Util.Utilities;
import content_model.requestCM;
public class MainAction  extends Action
{
	User user=null;
	HttpSession session=null;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) 
	{ 
		ActionForward forward=null;
		
		String subaction=request.getParameter("subaction");
		System.out.println("subaction: "+subaction);
		if(subaction.equals("raiseRequest"))
			{
			forward=raiseRequest(mapping,form,request,response);
			
			}
		else if (subaction.equals("dashboard"))
		{
			forward=dashboard(mapping,form,request,response);
		}
		else if(subaction.equals("login"))
		{
			login(mapping,form,request,response);
			forward=dashboard(mapping, form, request, response);
		}
		else if(subaction.equals("retrieveState"))
		{
			retrieveState(mapping,form,request,response);
		}
		else if(subaction.equals("retrieveCity"))
		{
			retrieveCity(mapping,form,request,response);
		}
		else if(subaction.equals("populateNGO"))
		{
			populateNGO(mapping,form,request,response);
		}
		else if(subaction.equals("viewRequest"))
		{
			forward=viewRequest(mapping,form,request,response);
		}
		else if(subaction.equals("approveRequest"))
		{
			forward=approveRequestList(mapping,form,request,response);
		}
		else if(subaction.equals("orgList"))
		{
			forward=orgList(mapping,form,request,response);
		}
		else if(subaction.equals("logout"))
		{
			forward=logout(mapping,form,request,response);
		}
		else if(subaction.equals("joinOrg"))
			forward=joinOrg(mapping, form, request, response);
		else if(subaction.equals("exitGroup"))
			forward=exitGroup(mapping, form, request, response);
		else if(subaction.equals("signUp"))
			forward=signUp(mapping, form, request, response);
		else if(subaction.equals("createUser"))
			forward=createUser(mapping, form, request, response);
		else if(subaction.equals("acceptRequest"))
			forward=acceptRequest(mapping, form, request, response);
		else if(subaction.equals("acceptUser"))
			forward=acceptUser(mapping, form, request, response);
		else if(subaction.equals("assignUser"))
			forward=assignUser(mapping, form, request, response);
		else if(subaction.equals("checkData"))
			checkData(mapping,form,request,response);
		else if(subaction.equals("populateUser"))
			populateUser(mapping, form, request, response);
		if(forward!=null)

			return forward;
		else
		return mapping.findForward(Constants.CREATED);
		
		
		
	}

	private ActionForward raiseRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		try
		{
			boolean status=false;
			 user=(User) form;
		requestCM rcm=new requestCM();
		//rcm.raiseRequestCM();
		HashMap<Integer,String> countryLists=rcm.retrieveCountry();
		ArrayList<Country> countryList = new ArrayList<Country>();
		String countryId=request.getParameter("countryId");
		String cityId=request.getParameter("cityId");
		String stateId=request.getParameter("stateId");
		String ngo=request.getParameter("ngo");
		String area=request.getParameter("area");
		
		/*
		 * String photopath=request.getParameter("photo");
		 * 
		 * if(Utilites.hasValue(photopath)) { File photo=new File(photopath); }
		 */
        
        String description=request.getParameter("description");
      
	if(Utilities.hasValue(ngo) && Utilities.hasValue(countryId)&& Utilities.hasValue(cityId)&& Utilities.hasValue(stateId) && Utilities.hasValue(area)&&Utilities.hasValue(description) )
	{
		
		status=rcm.raiseRequestCM(countryId,stateId,cityId,ngo,area,description);
	}
	if(status)
	{
		user.setMessage("Request Raised Successfully");
		return dashboard(mapping, form, request, response);
	}
	else
	{
		if(countryLists!=null)
		{
			for (Entry<Integer, String> mapElement : countryLists.entrySet()) {
	            int key = mapElement.getKey();
	            String value=mapElement.getValue();
	            countryList.add(new Country(String.valueOf(key), value));
	       }
		}
		user.setCountryList(countryList);
		request.setAttribute("countryList", countryList);
		}
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mapping.findForward("raiseRequest");
	}
	private JSONArray retrieveState(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		JSONArray data=null;
		PrintWriter out=null;
		
		try
		{
			
			 
			String countryId=request.getParameter("countryId");
			System.out.println("CountryId:"+countryId);
			requestCM rcm=new requestCM();
			data=new JSONArray();
			data=rcm.retrieveState(countryId);
			out = response.getWriter();
			out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
		return data;
	}
	private JSONArray retrieveCity(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		JSONArray data=null;
		PrintWriter out=null;
		
		try
		{
			 data=new JSONArray();
			String countryId=request.getParameter("stateId");
			requestCM rcm=new requestCM();
			data=rcm.retrieveCity(countryId);
			JSONArray d=new JSONArray();
			
			 out = response.getWriter();
			 out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
		return data;
	}
	private JSONArray populateNGO(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		JSONArray data=null;
		PrintWriter out=null;
		
		try
		{
			 data=new JSONArray();
			String countryId=request.getParameter("countryId");
			String cityId=request.getParameter("cityId");
			String stateId=request.getParameter("stateId");
			requestCM rcm=new requestCM();
			data=rcm.populateNGO(countryId,stateId,cityId);
			JSONArray d=new JSONArray();
			System.out.println("Checking NGO "+ data);
			 out = response.getWriter();
			 out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
		return data;
	}
	private JSONArray populateUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		JSONArray data=null;
		PrintWriter out=null;
		
		try
		{
			 data=new JSONArray();
			String orgId=request.getParameter("orgId");
			requestCM rcm=new requestCM();
			data=rcm.populateUser(orgId);
			JSONArray d=new JSONArray();
			
			 out = response.getWriter();
			 out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
		return data;
	}
	private JSONArray checkData(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		JSONArray data=null;
		PrintWriter out=null;
		
		try
		{
			 data=new JSONArray();
			String type=request.getParameter("type");
			String value=request.getParameter("value");
			
			requestCM rcm=new requestCM();
			data=rcm.checkData(type,value);
			JSONArray d=new JSONArray();
			
			 out = response.getWriter();
			 out.println(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
		return data;
	}
	private ActionForward viewRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		ArrayList<ViewStatus> data=null;
		 user=(User) form;
		try
		{
			System.out.println("User id is "+session.getAttribute("id"));
			long id=(long) session.getAttribute("id");
			requestCM rcm=new requestCM();
			data=rcm.retrieveViewStatusList(id);
			user.setViewStatusList(data);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("viewStatus");
	}
	private ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		 user=(User) form;
		 PrintWriter out=null;
		 long userid=0;
		try
		{
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			requestCM rcm=new requestCM();
			ArrayList<String> data=rcm.login(id, password);
			if(data!=null)
			{
				user.setUser_id(String.valueOf(data.get(0)));
				user.setUser_type(data.get(1));
				session = request.getSession();
				session.setAttribute("id", Long.parseLong(data.get(0)));
				session.setAttribute("userType", data.get(1));
				session.setAttribute("orgId", Long.parseLong(data.get(2)));
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward(Constants.SUCCESS);
		}
	
	private ActionForward dashboard(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		 user=(User) form;
		 
		try
		{
			
			requestCM rcm=new requestCM();
			JSONArray data=new JSONArray();
			data=rcm.dashboard();
			if(data!=null)
			{
				
				user.setNo_of_users(String.valueOf(data.getInt(0)));
				user.setNo_of_org(String.valueOf(data.getInt(1)));
				user.setNo_of_request_raised(String.valueOf(data.getInt(2)));
				user.setNo_of_requests_solved(String.valueOf(data.getInt(3)));
				user.setName(data.getString(4));
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward(Constants.SUCCESS);
		}
	
	private ActionForward acceptRequest(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		 user=(User) form;
		 
		try
		{
			String userId=request.getParameter("id");
			String requestId=request.getParameter("reqeustNo");
			String action=request.getParameter("action");
			requestCM rcm = new requestCM();
			rcm.requestAction(requestId, action);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("approveRequest");
		}
	
	private ActionForward approveRequestList(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		 user=(User) form;
		 
		try
		{
			String userId=user.getUser_id();
			requestCM rcm=new requestCM();
			 ArrayList<ApproveList> data=new  ArrayList<ApproveList>();
			data=rcm.approveRequestList(userId);
			user.setApproveList(data);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("approveRequest");
		}
	
	private ActionForward orgList(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		 user=(User) form;
		 ArrayList<Organization>data=new ArrayList<Organization>();
		try
		{
			
			requestCM rcm=new requestCM();
			String userId=user.getUser_id();
			data=rcm.orgList(userId );
			user.setOrgList(data);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("orgList");
		}
	
	private ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
	try
		{	
			session.removeAttribute("id");
			session.invalidate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("logout");
		}
	private ActionForward joinOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
	try
		{	
			String userId=request.getParameter("id");
			String orgId=request.getParameter("orgId");
			requestCM rcm=new requestCM();
			rcm.joinOrg(userId,orgId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return orgList(mapping, form, request, response);
		}
	private ActionForward exitGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		try
			{	
				String userId=request.getParameter("id");
				String orgId=request.getParameter("orgId");
				requestCM rcm=new requestCM();
				rcm.exitGroup(userId,orgId);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				
			}
			return orgList(mapping, form, request, response);
			}
	private ActionForward signUp(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		try
			{	
			 user=(User) form;
			requestCM rcm=new requestCM();
			ArrayList<Country> countryList = new ArrayList<>();
			HashMap<Integer,String> countryLists=rcm.retrieveCountry();
			
			if(countryLists!=null)
			{
				for (Entry<Integer, String> mapElement : countryLists.entrySet()) {
		            int key = mapElement.getKey();
		            String value=mapElement.getValue();
		            countryList.add(new Country(String.valueOf(key), value));
		       }
			}
			user.setCountryList(countryList);
			request.setAttribute("countryList", countryList);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				
			}
			return mapping.findForward("signUp");
			}
	
	private ActionForward createUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		try
			{	
			 user=(User) form;
			requestCM rcm=new requestCM();
			String usertype=request.getParameter("usertype");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String country=request.getParameter("country");
			String state=request.getParameter("state");
			String city=request.getParameter("city");
			String password=request.getParameter("password");
			ArrayList<Country> countryList = new ArrayList<>();
			HashMap<Integer,String> countryLists=rcm.retrieveCountry();
			long id=rcm.creatUser(usertype, name, email, country, state, city, password);
			
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			//return dashboard(mapping, form, request, response)	;
			return mapping.findForward("Constants.SUCCESS");
			}
	private ActionForward acceptUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		 user=(User) form;
		ArrayList<AcceptUser> data=new ArrayList<>();
		try
		{
			String orgId=request.getParameter("orgId");
			requestCM rcm=new requestCM();
			data=rcm.acceptUser(orgId);
			user.setAcceptUser(data);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("acceptUser");
		}
	private ActionForward assignUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,  HttpServletResponse response) {
		
		ArrayList<ViewStatus> data=null;
		 user=(User) form;
		try
		{
			
			long orgId=(long) session.getAttribute("orgId");
			requestCM rcm=new requestCM();
			data=rcm.retrieveViewStatusList(orgId);
			user.setViewStatusList(data);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		return mapping.findForward("assignUser");
		}
}
