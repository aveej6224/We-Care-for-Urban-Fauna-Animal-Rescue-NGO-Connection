
# We Care: Web-based Animal Rescue & NGO Coordination System

A web-based platform that connects citizens, NGOs, and volunteers to rescue and care for injured animals in urban areas.  
The system enables users to raise requests for injured animals, NGOs to triage them, and volunteers to be assigned for rescue.  

---

## Features
- **Report Injured Animals**: Citizens can raise requests with details and photos.  
- **NGO Dashboard**: Review, approve, assign, or reject rescue requests.  
- **Volunteer Coordination**: Assign rescuers and track case resolution.  
- **Join/Leave Organizations**: Users can become part of NGOs and contribute directly.  
- **Real-Time Updates**: Track case progress from submission to closure.  

---

## Modules
- **Raise Request** – Submit details and photos of injured animals.  
- **Approve Request** – NGOs validate and authenticate reports.  
- **Assign User** – Requests are assigned to specific volunteers for resolution.  
- **Join Organization** – Citizens can request to join NGOs.  
- **Accept/Reject Join Request** – NGOs manage membership requests.  
- **Leave Organization** – Volunteers can leave NGOs when needed.  

---

## System Architecture
The platform follows an **MVC architecture** using Struts 2.0.  
- **Frontend**: Web forms and interactive UI with Bootstrap, jQuery, and AJAX.  
- **Controller**: Struts 2.0 actions handling requests and responses.  
- **Model**: Business logic with Java classes.  
- **Database**: MySQL to persist user, NGO, and case data.  

---

## Technologies Used
- **Backend:** Java (Struts Framework)  
- **Frontend:** HTML5, CSS3, JavaScript, Bootstrap, AJAX, jQuery  
- **Database:** MySQL  
- **Server:** Apache Tomcat  
- **IDE:** Eclipse  

---

## Requirements

### Software
- Java (JDK 8 or later)  
- Apache Tomcat  
- MySQL Server  
- Eclipse IDE (or any Java IDE)  
- Web Browser (Chrome recommended)  

### Hardware
- Processor: Intel i3 or above  
- RAM: 8 GB+  
- Storage: 5 GB free space  

---

## Applications
- Rescue and welfare of stray/injured animals.  
- Community participation in rescue operations.  
- NGO efficiency improvements via streamlined workflows.  
- Data collection on animal welfare issues.  

---

## Advantages
- **Fast & Efficient Reporting** with real-time updates.  
- **Collaboration** between NGOs, citizens, and volunteers.  
- **Data-Driven Insights** for better decision-making.  

---

## Limitations
- Dependent on internet connectivity.  
- Risk of false/unauthenticated reports.  
- Limited to regions with active NGOs/volunteers.  

---

## Future Scope
- **Geo-Tagging**: Auto-locate and assign cases to nearest NGO.  
- **Live Chat**: Direct communication between citizens and NGOs.  
- **Adoption Services**: Extend to fostering/adopting rescued animals.  
- **Mobile App Support**: Native apps for Android/iOS.  

---

## Getting Started

1. Clone the repository:  
   ```bash
   git clone https://github.com/aveej6224/We-Care-for-Urban-Fauna-Animal-Rescue-NGO-Connection.git
   ```

2. Import into Eclipse IDE.  

3. Configure the MySQL database and update connection details in `web.xml`.  

4. Deploy the project on Apache Tomcat server.  

5. Open in browser:  
   ```
   http://localhost:8080/wecare
   ```

---
