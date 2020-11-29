package com.sjsu.cmpe202.search.retail.listing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sjsu.cmpe202.property.repositories.PropertyRepository;
import com.sjsu.cmpe202.search.retail.listing.model.Property;



@Service
public class PropertyServicesImpl implements PropertyServices{

    private PropertyRepository propertyRepository;

    @Autowired
    EntityManager em;
    
    @Autowired
    public PropertyServicesImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }


    @Override
    public List<Property> listAll() {
        List<Property> propertys = new ArrayList<>();
        propertyRepository.findAll().forEach(propertys::add);
        return propertys;
    }

    @Override
    public Iterable<Property> getById(Long id) {
    	
        List <Long> ids = new ArrayList<Long>();
        ((ArrayList<Long>) ids).add(id);
		return propertyRepository.findAllById(ids);
    }

    @Override
    public Property saveOrUpdate(Property property) {
        propertyRepository.save(property);
        return property;
    }

    @Override
    public void delete(Long id) {
        propertyRepository.deleteById(id);

    }


	@Override
	public TypedQuery<Property> constructQuery(Map<String, String> customQuery) {
		CriteriaBuilder cb = null;
		try {
	    	 cb = em.getCriteriaBuilder();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    	CriteriaQuery<Property> cq = cb.createQuery(Property.class);
	    	Root<Property> property = cq.from(Property.class);
	    	Predicate predicate = null;
	    	
	    	try {
	    	
	    	for (Map.Entry<String,String> entry : customQuery.entrySet())  
	    	{
	            
	            if(property.get(entry.getKey().toString()) != null)
	            {
	            	
	            	//Query for range values with comma(,) as delimiter
	            	if(entry.getValue().contains(","))
	            	{
	            		int minRange=Integer.parseInt(customQuery.get(entry.getKey().toString()).split(",")[0]);
	            		int maxRange=Integer.parseInt(customQuery.get(entry.getKey().toString()).split(",")[1]);
	            		predicate = cb.between(property.get(entry.getKey().toString()),minRange, maxRange );
	            	}
	            	//Query for equals values
	            	else
	            	{
	            	 predicate = cb.equal(property.get(entry.getKey().toString()), customQuery.get(entry.getKey().toString()));
	            	}
	            }
	    	}
	    	}catch (Exception e) {
				e.printStackTrace();
			}
	        cq.where(predicate);
	        TypedQuery<Property> query = em.createQuery(cq);
	        return query;
	}


}