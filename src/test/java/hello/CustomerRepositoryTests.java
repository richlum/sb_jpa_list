/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTests {


	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customers;

    @Test
    public void testFindByLastName() {
    	List<String> nn = new ArrayList<String>();
    	nn.add("one");
    	nn.add("two");
    	nn.add("three");
        Customer customer = new Customer("first", "last", nn);
        entityManager.persist(customer);

        List<Customer> findByLastName = customers.findByLastName(customer.getLastName());
        findByLastName.forEach((Customer c) -> {
        	log.info("lastname:" + c.toString());
        });
        
        assertThat(findByLastName).extracting(Customer::getLastName).containsOnly(customer.getLastName());
    }
}