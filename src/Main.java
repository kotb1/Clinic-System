import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import java.time.LocalDate;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;

public class Main {

	public static void main(String[] args) {
		ArrayList <Patient> p = new ArrayList <Patient>();
		ArrayList <Interactions> inter = new ArrayList <Interactions>();
		Model model =  ModelFactory.createDefaultModel();
		Scanner s = new Scanner (System.in);
		InputStream in = FileManager.get().open("C:\\Users\\bodyy\\OneDrive\\Desktop\\test.owl");
		if(in==null) 
		{
			throw new IllegalArgumentException("File Not Found");
		}
		model.read(in,null);
		
		String u = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Pat_Name";
		Property prop = model.createProperty(u);
		ResIterator i = model.listSubjectsWithProperty(prop);
		System.out.println("All Patients name: ");
		while(i.hasNext()) 
		{
			System.out.println("	"+i.nextResource().getProperty(prop).getString());
		}
		System.out.println("Please Enter the name of the patient");
		String name = s.nextLine();
		String name_uri = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#"+name;
		String medicine_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Take_Medication";
		Property medicine_prop = model.createProperty(medicine_uri);
		org.apache.jena.rdf.model.Resource patient =model.getResource(name_uri);
		String medicine_name_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Med_Name";
		Property medicine_name_prop = model.createProperty(medicine_name_uri);
		StmtIterator ii = ((org.apache.jena.rdf.model.Resource) patient).listProperties(medicine_prop);
		while(ii.hasNext()) 
		{
			System.out.println(ii.nextStatement().getProperty(medicine_name_prop).getString());
		}
		String disease_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Has_Disease";
		Property disease_prop = model.createProperty(disease_uri);
		org.apache.jena.rdf.model.Resource patient2 =model.getResource(name_uri);		
		String disease_name_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Has_Name";
		Property disease_name_prop = model.createProperty(disease_name_uri);
		StmtIterator ii2 = ((org.apache.jena.rdf.model.Resource) patient2).listProperties(disease_prop);
		while(ii2.hasNext()) 
		{
			System.out.println(ii2.nextStatement().getProperty(disease_name_prop).getString());
		}
		
		ArrayList <String> person = new ArrayList <String> ();
		String u2 = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Pat_Name";
		Property prop2 = model.createProperty(u2);
		ResIterator i2 = model.listSubjectsWithProperty(prop2);
		System.out.println("Alerted Patients: ");
		while(i2.hasNext()) 
		{
			person.add(i2.nextResource().toString());
		}
		for(int q =0;q<person.size();q++) 
		{
			String medicine_uri2="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Take_Medication";
			Property medicine_prop2 = model.createProperty(medicine_uri2);
			org.apache.jena.rdf.model.Resource patients =model.getResource(person.get(q));
			String medicine_startdate_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Start_Date";
			Property medicine_startdate_prop = model.createProperty(medicine_startdate_uri);
			String medicine_enddate_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#End_Date";
			Property medicine_enddate_prop = model.createProperty(medicine_enddate_uri);
			StmtIterator ii3 = ((org.apache.jena.rdf.model.Resource) patients).listProperties(medicine_prop2);
			StmtIterator ii4 = ((org.apache.jena.rdf.model.Resource) patients).listProperties(medicine_prop2);
			StmtIterator ii5 = ((org.apache.jena.rdf.model.Resource) patients).listProperties(medicine_prop2);
			Patient v = new Patient();
			v.Patient_name=person.get(q).substring(person.get(q).lastIndexOf("#") + 1);
			while(ii3.hasNext()) 
			{
				Medicine m = new Medicine();
				m.Start_Date=ii3.nextStatement().getProperty(medicine_startdate_prop).getString();
				m.End_Date=ii5.nextStatement().getProperty(medicine_enddate_prop).getString();	
				m.Medicine_name=ii4.nextStatement().getProperty(medicine_name_prop).getString();
				v.meds.add(m);
			}
			p.add(v);
		}	
		
		
		ArrayList <String> Medicines = new ArrayList<String>();
		String u_2 = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Med_Name";
		Property prop_2 = model.createProperty(u_2);
		ResIterator i02 = model.listSubjectsWithProperty(prop_2);
		while(i02.hasNext()) 
		{
			Medicines.add((i02.nextResource()).toString());
		}
		
		for(int s1=0;s1<Medicines.size();s1++) 
		{
			Interactions i5=null;
			String meduri=Medicines.get(s1);
			org.apache.jena.rdf.model.Resource chks =model.getResource(meduri);
			String intera= "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Major";
			Property aprop2 = model.createProperty(intera);
			String medname="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Med_Name";
			Property aprop = model.createProperty(medname);
			StmtIterator ii7 = ((org.apache.jena.rdf.model.Resource) chks).listProperties(aprop2);
			while(ii7.hasNext()) 
			{
				i5= new Interactions();
				i5.Severity="Major";
				i5.Medicine1=meduri.substring(meduri.lastIndexOf("#") + 1);
				i5.Medicine2=ii7.nextStatement().getProperty(aprop).getString();
				inter.add(i5);
			}
			Interactions i6=null;
			String meduri2=Medicines.get(s1);
			org.apache.jena.rdf.model.Resource chks2 =model.getResource(meduri2);
			String intera2= "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Minor";
			Property aprop3 = model.createProperty(intera2);
			String medname2="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Med_Name";
			Property aprop4 = model.createProperty(medname2);
			StmtIterator ii8 = ((org.apache.jena.rdf.model.Resource) chks2).listProperties(aprop3);
			while(ii8.hasNext()) 
			{
				i6= new Interactions();
				i6.Severity="Minor";
				i6.Medicine1=meduri.substring(meduri.lastIndexOf("#") + 1);
				i6.Medicine2=ii8.nextStatement().getProperty(aprop4).getString();
				inter.add(i6);
			}
			Interactions i7=null;
			String meduri3=Medicines.get(s1);
			org.apache.jena.rdf.model.Resource chks3 =model.getResource(meduri3);
			String intera3= "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Moderate";
			Property aprop5 = model.createProperty(intera3);
			String medname3="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Med_Name";
			Property aprop6 = model.createProperty(medname3);
			StmtIterator ii9 = ((org.apache.jena.rdf.model.Resource) chks3).listProperties(aprop5);
			while(ii9.hasNext()) 
			{
				i7= new Interactions();
				i7.Severity="Moderate";
				i7.Medicine1=meduri.substring(meduri.lastIndexOf("#") + 1);
				i7.Medicine2=ii9.nextStatement().getProperty(aprop6).getString();
				inter.add(i7);
			}
		}
		/*for(int u1=0;u1<inter.size();u1++) 
		{
			System.out.println(inter.get(u1).Severity+":	"+inter.get(u1).Medicine1+","+inter.get(u1).Medicine2);
		}*/
		
		
		
		for(int x1=0;x1<p.size();x1++) 
		{
			System.out.println(p.get(x1).Patient_name);
			if(p.get(x1).meds.size()== 3) {
				ArrayList <String> ux = new ArrayList<String>();
				String x1_start=p.get(x1).meds.get(0).Start_Date;
				int start1 = x1_start.indexOf("/");
				int start_day1=Integer.parseInt(x1_start.substring(0,start1));
				int start_month1= Integer.parseInt(x1_start.substring(start1+1,x1_start.lastIndexOf("/")));
				int start_year1=Integer.parseInt(x1_start.substring(x1_start.lastIndexOf("/") + 1));
				LocalDate start= LocalDate.of(start_year1,start_month1,start_day1);
				String x1_end=p.get(x1).meds.get(0).End_Date;
				int end1 = x1_end.indexOf("/");
				int end_day1=Integer.parseInt(x1_end.substring(0,end1));
				int end_month1= Integer.parseInt(x1_end.substring(end1+1,x1_end.lastIndexOf("/")));
				int end_year1=Integer.parseInt(x1_end.substring(x1_end.lastIndexOf("/") + 1));
				LocalDate end= LocalDate.of(end_year1,end_month1,end_day1+1);
				List <LocalDate> k=start.datesUntil(end).collect(Collectors.toList());
				
				
				String x2_start=p.get(x1).meds.get(1).Start_Date;
				int start2 = x2_start.indexOf("/");
				int start_day2=Integer.parseInt(x2_start.substring(0,start2));
				int start_month2= Integer.parseInt(x2_start.substring(start2+1,x2_start.lastIndexOf("/")));
				int start_year2=Integer.parseInt(x2_start.substring(x2_start.lastIndexOf("/") + 1));
				LocalDate start_2= LocalDate.of(start_year2,start_month2,start_day2);
				String x2_end=p.get(x1).meds.get(1).End_Date;
				int end2 = x2_end.indexOf("/");
				int end_day2=Integer.parseInt(x2_end.substring(0,end2));
				int end_month2= Integer.parseInt(x2_end.substring(end2+1,x2_end.lastIndexOf("/")));
				int end_year2=Integer.parseInt(x2_end.substring(x2_end.lastIndexOf("/") + 1));
				LocalDate end_2= LocalDate.of(end_year2,end_month2,end_day2+1);
				List <LocalDate> k2=start_2.datesUntil(end_2).collect(Collectors.toList());
				
				String x3_start=p.get(x1).meds.get(2).Start_Date;
				int start3 = x3_start.indexOf("/");
				int start_day3=Integer.parseInt(x3_start.substring(0,start3));
				int start_month3= Integer.parseInt(x3_start.substring(start3+1,x3_start.lastIndexOf("/")));
				int start_year3=Integer.parseInt(x3_start.substring(x3_start.lastIndexOf("/") + 1));
				LocalDate start_3= LocalDate.of(start_year3,start_month3,start_day3);
				String x3_end=p.get(x1).meds.get(2).End_Date;
				int end3 = x3_end.indexOf("/");
				int end_day3=Integer.parseInt(x3_end.substring(0,end3));
				int end_month3= Integer.parseInt(x3_end.substring(end3+1,x3_end.lastIndexOf("/")));
				int end_year3=Integer.parseInt(x3_end.substring(x3_end.lastIndexOf("/") + 1));
				LocalDate end_3= LocalDate.of(end_year3,end_month3,end_day3+1);
				List <LocalDate> k3=start_3.datesUntil(end_3).collect(Collectors.toList());
				if(true)
				{
					outerloop:
					for(int q =0;q<k.size();q++) 
					{
						for(int o=0;o<k2.size();o++) 
						{
							if(k.get(q).equals(k2.get(o))) 
							{
								for(int j1=0;j1<inter.size();j1++) 
								{
									if((inter.get(j1).Medicine1.equals(p.get(x1).meds.get(0).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(1).Medicine_name))||
											(inter.get(j1).Medicine1.equals(p.get(x1).meds.get(1).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(0).Medicine_name))) 
									{
										System.out.println("Severity: "+inter.get(j1).Severity);
										System.out.println("{"+p.get(x1).meds.get(0).Medicine_name+","+p.get(x1).meds.get(1).Medicine_name+"}"+"-");
									}
								}		
								break outerloop;
							}
						}
					}
					outerloop2:
					for(int q =0;q<k.size();q++) 
					{
						for(int o=0;o<k3.size();o++) 
						{
							if(k.get(q).equals(k3.get(o))) 
							{
								for(int j1=0;j1<inter.size();j1++) 
								{
									if((inter.get(j1).Medicine1.equals(p.get(x1).meds.get(0).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(2).Medicine_name))||
											(inter.get(j1).Medicine1.equals(p.get(x1).meds.get(2).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(0).Medicine_name))) 
									{
										System.out.println("Severity: "+inter.get(j1).Severity);
										System.out.println("{"+p.get(x1).meds.get(0).Medicine_name+","+p.get(x1).meds.get(2).Medicine_name+"}"+"-");
									}
								}		
								break outerloop2;
							}
						}
					}
				}
				
				
				
				
				if(true)
				{
					outerloop:
					for(int q =0;q<k2.size();q++) 
					{
						for(int o=0;o<k3.size();o++) 
						{
							if(k2.get(q).equals(k3.get(o))) 
							{
								for(int j1=0;j1<inter.size();j1++) 
								{
									if((inter.get(j1).Medicine1.equals(p.get(x1).meds.get(1).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(2).Medicine_name))||
											(inter.get(j1).Medicine1.equals(p.get(x1).meds.get(2).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(1).Medicine_name))) 
									{
										System.out.println("Severity: "+inter.get(j1).Severity);
										System.out.println("{"+p.get(x1).meds.get(1).Medicine_name+","+p.get(x1).meds.get(2).Medicine_name+"}"+"-");
									}
								}		
								break outerloop;
							}
						}
					}
				}
				System.out.println();
			}
			else 
			{
				ArrayList <String> ux = new ArrayList<String>();
				String x1_start=p.get(x1).meds.get(0).Start_Date;
				int start1 = x1_start.indexOf("/");
				int start_day1=Integer.parseInt(x1_start.substring(0,start1));
				int start_month1= Integer.parseInt(x1_start.substring(start1+1,x1_start.lastIndexOf("/")));
				int start_year1=Integer.parseInt(x1_start.substring(x1_start.lastIndexOf("/") + 1));
				LocalDate start= LocalDate.of(start_year1,start_month1,start_day1);
				String x1_end=p.get(x1).meds.get(0).End_Date;
				int end1 = x1_end.indexOf("/");
				int end_day1=Integer.parseInt(x1_end.substring(0,end1));
				int end_month1= Integer.parseInt(x1_end.substring(end1+1,x1_end.lastIndexOf("/")));
				int end_year1=Integer.parseInt(x1_end.substring(x1_end.lastIndexOf("/") + 1));
				LocalDate end= LocalDate.of(end_year1,end_month1,end_day1+1);
				List <LocalDate> k=start.datesUntil(end).collect(Collectors.toList());
				
				String x2_start=p.get(x1).meds.get(1).Start_Date;
				int start2 = x2_start.indexOf("/");
				int start_day2=Integer.parseInt(x2_start.substring(0,start2));
				int start_month2= Integer.parseInt(x2_start.substring(start2+1,x2_start.lastIndexOf("/")));
				int start_year2=Integer.parseInt(x2_start.substring(x2_start.lastIndexOf("/") + 1));
				LocalDate start_2= LocalDate.of(start_year2,start_month2,start_day2);
				String x2_end=p.get(x1).meds.get(1).End_Date;
				int end2 = x2_end.indexOf("/");
				int end_day2=Integer.parseInt(x2_end.substring(0,end2));
				int end_month2= Integer.parseInt(x2_end.substring(end2+1,x2_end.lastIndexOf("/")));
				int end_year2=Integer.parseInt(x2_end.substring(x2_end.lastIndexOf("/") + 1));
				LocalDate end_2= LocalDate.of(end_year2,end_month2,end_day2+1);
				List <LocalDate> k2=start_2.datesUntil(end_2).collect(Collectors.toList());
				outerloop:
					for(int q =0;q<k.size();q++) 
					{
						for(int o=0;o<k2.size();o++) 
						{
							if(k.get(q).equals(k2.get(o))) 
							{
								for(int j1=0;j1<inter.size();j1++) 
								{
									if((inter.get(j1).Medicine1.equals(p.get(x1).meds.get(0).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(1).Medicine_name))||
											(inter.get(j1).Medicine1.equals(p.get(x1).meds.get(1).Medicine_name) &&inter.get(j1).Medicine2.equals(p.get(x1).meds.get(0).Medicine_name))) 
									{
										System.out.println("Severity: "+inter.get(j1).Severity);
										System.out.println("{"+p.get(x1).meds.get(0).Medicine_name+","+p.get(x1).meds.get(1).Medicine_name+"}"+"-");
									}
								}						
								break outerloop;
							}
						}
					}
				System.out.println();
			}
			
		}
		
	}

}























//instance_resources.add((i02.nextResource()).toString());
//System.out.println((i02.nextResource()).toString());

//System.out.println(ii5.nextStatement().getProperty(medicine_enddate_prop).getString());
//System.out.println(ii3.nextStatement().getProperty(medicine_startdate_prop).getString());
//System.out.println(ii4.nextStatement().getProperty(medicine_name_prop).getString());
/*Medicine m = new Medicine(ii4.nextStatement().getProperty(medicine_name_prop).getString(),
ii3.nextStatement().getProperty(medicine_startdate_prop).getString(),
ii5.nextStatement().getProperty(medicine_enddate_prop).getString());*/
//model.write(System.out);




/*for(int p1 = 0;p1<p.size();p1++) 
{
	System.out.println(p.get(p1).Patient_name+":");
	for(int p2=0;p2<p.get(p1).meds.size();p2++) 
	{
		System.out.print(p.get(p1).meds.get(p2).Medicine_name+"		"+p.get(p1).meds.get(p2).Start_Date+"		"+p.get(p1).meds.get(p2).End_Date);
	}
	System.out.println();
}*/











//}
/*String name2_uri = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#"+name;
String disease_uri = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Has_Disease";
Property disease_prop = model.createProperty(disease_uri);
org.apache.jena.rdf.model.Resource patient2 =model.getResource(name2_uri);
//String disease_name_uri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Has_Name";
//Property disease_name_prop = model.createProperty(disease_name_uri);
StmtIterator ii2 = ((org.apache.jena.rdf.model.Resource) patient2).listProperties(disease_prop);
while(ii2.hasNext())       //Tari2a tanya betshta8al nas ahh wa nas laa
{
	org.apache.jena.rdf.model.Resource aa = model.getResource(ii2.nextStatement().getObject().toString());
	System.out.println(aa.getLocalName());
}
/*ArrayList <String> instance_resources = new ArrayList<String>();
String u2 = "http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Pat_Name";
Property prop2 = model.createProperty(u2);
ResIterator i2 = model.listSubjectsWithProperty(prop2);
//System.out.println("All Patients name: ");
while(i2.hasNext()) 
{
	instance_resources.add((i2.nextResource()).toString());
}
for(int i0=0;i0<instance_resources.size();i0++) 
{
	ArrayList <String> Medicines = new ArrayList<String>();
	String medicine_uris="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Take_Medication";
	Property medicine_props = model.createProperty(medicine_uris);
	org.apache.jena.rdf.model.Resource patients =model.getResource(instance_resources.get(i0));
	String medicine_name_uris="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Med_Name";
	Property medicine_name_props = model.createProperty(medicine_name_uris);
	StmtIterator iis = ((org.apache.jena.rdf.model.Resource) patients).listProperties(medicine_props);
	while(iis.hasNext()) 
	{
		Medicines.add((iis.nextStatement()).toString());
	}
	//Resource chks = model.getProperty(patients, medicine_name_props);
	for(int b=0;b<Medicines.size();b++) 
	{
		//Literal meds=model.createLiteral(Medicines.get(b));
		//System.out.println(meds.getLexicalForm());
		org.apache.jena.rdf.model.Resource meds=model.getResource(Medicines.get(b));
		String StartDateuri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Start_Date";
		Property startdate = model.getProperty(StartDateuri);
		System.out.println(model.getProperty(Medicines.get(b), StartDateuri));
		/*ArrayList <String> Dates= new ArrayList<String>();
		String StartDateuri="http://www.semanticweb.org/youss/ontologies/2021/11/untitled-ontology-6#Start_Date";
		Property startdate = model.getProperty(StartDateuri);
		org.apache.jena.rdf.model.Resource meds=model.getResource(Medicines.get(b));
		StmtIterator qq = ((org.apache.jena.rdf.model.Resource) meds).listProperties(startdate);
		while(qq.hasNext()) 
		{
			
			//Medicines.add((qq.nextStatement()).toString());
			//System.out.println(qq.nextStatement().getObject().toString());
			org.apache.jena.rdf.model.Resource aa = model.getResource(qq.nextStatement().getObject().toString());
			System.out.println(aa.getLocalName());
		}*/
	//}
	
	
	
	
	/*for(int z=0;z<Medicines.size();z++) 
	{
		System.out.println(Medicines.get(z));
	}*/
//}








/*ArrayList <String> possible_values = new ArrayList<String>();
if(possible_values.size()== 3) {
ArrayList <String> ux = new ArrayList<String>();
LocalDate end= LocalDate.of(2021,7,6);
LocalDate start= LocalDate.of(2021,7,1);
List <LocalDate> k=start.datesUntil(end).collect(Collectors.toList());//body
LocalDate end2= LocalDate.of(2021,4,16);
LocalDate start2= LocalDate.of(2021,4,1);
List <LocalDate> k2=start2.datesUntil(end2).collect(Collectors.toList());//kotb
LocalDate end3= LocalDate.of(2021,3,6);
LocalDate start3= LocalDate.of(2021,3,3);
List <LocalDate> k3=start3.datesUntil(end3).collect(Collectors.toList());//abdo
boolean x = false;
if(!(ux.contains("body"))) 
{
	outerloop:
	for(int q =0;q<k.size();q++) 
	{
		for(int o=0;o<k2.size();o++) 
		{
			if(k.get(q).equals(k2.get(o))) 
			{
				ux.add("body");
				ux.add("kotb");
				break outerloop;
			}
		}
	}
	outerloop2:
	for(int q =0;q<k.size();q++) 
	{
		for(int o=0;o<k3.size();o++) 
		{
			if(k.get(q).equals(k3.get(o))) 
			{
				if(!(ux.contains("body"))) 
				{
					ux.add("body");
				}
				ux.add("abdo");
				break outerloop2;
			}
		}
	}
}
if(!(ux.contains("kotb"))) 
{
	outerloop:
	for(int q =0;q<k2.size();q++) 
	{
		for(int o=0;o<k3.size();o++) 
		{
			if(k2.get(q).equals(k3.get(o))) 
			{
				ux.add("kotb");
				ux.add("abdo");
				break outerloop;
			}
		}
	}
}
System.out.println(ux);
}
else 
{
ArrayList <String> ux = new ArrayList<String>();
LocalDate end= LocalDate.of(2021,7,6);
LocalDate start= LocalDate.of(2021,7,1);
List <LocalDate> k=start.datesUntil(end).collect(Collectors.toList());//body
LocalDate end2= LocalDate.of(2021,4,16);
LocalDate start2= LocalDate.of(2021,4,1);
List <LocalDate> k2=start2.datesUntil(end2).collect(Collectors.toList());//kotb
boolean x = false;
outerloop:
	for(int q =0;q<k.size();q++) 
	{
		for(int o=0;o<k2.size();o++) 
		{
			if(k.get(q).equals(k2.get(o))) 
			{
				ux.add("body");
				ux.add("kotb");
				break outerloop;
			}
		}
	}
}*/