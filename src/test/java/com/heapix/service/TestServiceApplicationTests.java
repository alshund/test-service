package com.heapix.service;

import com.heapix.service.config.JwtTokenProperties;
import com.heapix.service.repository.CustomerRepo;
import com.heapix.service.repository.PartnerRepo;
import com.heapix.service.repository.entity.CustomerEntity;
import com.heapix.service.repository.entity.PartnerEntity;
import com.heapix.service.reusable.AccountStatus;
import com.heapix.service.service.CustomerService;
import com.heapix.service.service.PartnerService;
import com.heapix.service.service.bo.CreatePartnerBo;
import com.heapix.service.service.bo.CustomerBo;
import com.heapix.service.service.bo.InitialsBo;
import com.heapix.service.service.bo.PartnerBo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceApplicationTests {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private PartnerRepo partnerRepo;
	@Autowired
    private JwtTokenProperties jwtConfig;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private PartnerService partnerService;

	@Test
	public void getCustomer() {
		Random random = new Random();
		CustomerEntity customer = prepareCustomer(random, 1000);
		CustomerEntity added = customerRepo.save(customer);
		CustomerBo geted = customerService.getById(added.getId());

		Assert.assertEquals(added.getId(), geted.getId());
	}

	@Test
	public void getPartner() {
		Random random = new Random();
		CustomerEntity customer = customerRepo.save(prepareCustomer(random, 1000));
		CreatePartnerBo created = prepareCreatePartnerBo(random, 1000);
		PartnerBo added = partnerService.addPartner(customer.getId(), created);
		PartnerBo geted = partnerService.getById(added.getId());

		Assert.assertEquals(added, geted);
	}

	@Test
	public void loadData() {
		Random random = new Random();
		Set<PartnerEntity> partners = preparePartners(random, 1000);
		partnerRepo.saveAll(partners);
	}

	public List<CustomerEntity> prepareCustomers(Random random, int bound) {
		List<CustomerEntity> customers = new ArrayList<>();
		for (int index = 0; index < 20; index++) {
			CustomerEntity customer = prepareCustomer(random, bound);
			customers.add(customer);
		}
		return customers;
	}

	public CustomerEntity prepareCustomer(Random random, int bound) {
		return CustomerEntity
				.builder()
				.login("Customer Login " + random.nextInt(bound))
				.password("password " + random.nextInt(bound))
				.name("Customer Name " + random.nextInt(bound))
				.lastName("Customer Last Name " + random.nextInt(bound))
				.patronymic("Customer Patronymic " + random.nextInt(bound))
				.status(AccountStatus.ACTIVE)
				.balance(0.0f)
				.build();
	}

	public Set<PartnerEntity> preparePartners(Random random, int bound) {
		Set<PartnerEntity> partners = new HashSet<>();
		for (int i = 0; i < 10; i++) {
		    CustomerEntity customer = prepareCustomer(random, bound);
            for (int j = 0; j < 10; j++) {
                PartnerEntity partner = preparePartner(random, bound, customer);
                partners.add(partner);
            }
        }
		return partners;
	}

	public PartnerEntity preparePartner(Random random, int bound, CustomerEntity customer) {
		return  PartnerEntity
				.builder()
				.partnerId(random.nextLong())
				.accountId(random.nextLong())
				.name("Partner Name " + random.nextInt(bound))
				.lastName("Partner Last Name " + random.nextInt(bound))
				.patronymic("Patronymic " + random.nextInt(bound))
				.customer(customer)
				.build();
	}

	public CreatePartnerBo prepareCreatePartnerBo(Random random, int bound) {
		return CreatePartnerBo
				.builder()
				.accountId(random.nextLong())
				.partnerId(random.nextLong())
				.initials(
						InitialsBo
						.builder()
						.name("N")
						.lastName("L")
						.patronymic("P")
						.build()
				)
				.build();
	}
}
