package com.al.dao;

import java.util.List;


import com.al.model.Section;
/**
 * 
 * @author Administrator
 *interface @SectionDao
 */

public interface SectionDao 
{
	String TABLESection ="Section",COLsectionId="sectionId",COLsectionName= "sectionName";
	String COLdepartment="departmentid";
	Section getSection(int sectionId);
	 List<Section> getAllSection();
	 void addSection(Section section)throws  SectionExistException;
	 void updateSection(Section section);
	 void deleteSection(Section section);


}
