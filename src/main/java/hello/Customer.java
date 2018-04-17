// tag::sample[]
package hello;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    
    @ElementCollection(fetch=FetchType.EAGER)
//    @OneToMany(mappedBy="Customer",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<String> nickNames;

    public List<String> getNickNames() {
		return nickNames;
	}
    String nnresult = "";
    public String getNickNamesAsString() {
    	getNickNames().forEach((String name) -> {
    		if (nnresult.equals("")) {
    			nnresult = name;
    		}else {
    			nnresult = nnresult.concat(",").concat(name);
    		}
    	}); 
    	return nnresult;
    }
    
	public void setNickNames(List<String> nickNames) {
		this.nickNames = nickNames;
	}

	protected Customer() {}

    public Customer(String firstName, String lastName, List<String> nn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickNames = nn;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', nickNames='%s']",
                id, firstName, lastName, getNickNamesAsString());
    }

// end::sample[]

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}

