package cz.muni.fi.cdii.common.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@JsonAutoDetect(getterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE, 
	isGetterVisibility=Visibility.NONE, fieldVisibility=Visibility.DEFAULT)
public class Method implements Member {

    private String name;
    private Type type;
    private Bean producedBean;
    private List<MethodParameter> parameters = new ArrayList<>();
    /**
     * the only purpose of this field is equality computation
     */
    private Type surroundingType;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public Bean getProducedBean() {
        return producedBean;
    }
    
    public void setProducedBean(Bean producedBean) {
        this.producedBean = producedBean;
    }

    public List<MethodParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<MethodParameter> parameters) {
        this.parameters = parameters;
    }

    public Type getSurroundingType() {
        return surroundingType;
    }

    public void setSurroundingType(Type surroundingType) {
        this.surroundingType = surroundingType;
    }

    @Override
    public String getNodeText() {
        return this.getType().toString(false, true) + " " + this.getName() + "()";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surroundingType == null) ? 0 : surroundingType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Method other = (Method) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surroundingType == null) {
            if (other.surroundingType != null)
                return false;
        } else if (!surroundingType.equals(other.surroundingType))
            return false;
        return true;
    }
    
}
