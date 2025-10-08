package com.hendisantika.hris.springboothrissample1.config;

import com.hendisantika.hris.springboothrissample1.model.Department;
import com.hendisantika.hris.springboothrissample1.model.Employee;
import com.hendisantika.hris.springboothrissample1.model.Job;
import com.hendisantika.hris.springboothrissample1.repository.DepartmentRepository;
import com.hendisantika.hris.springboothrissample1.repository.EmployeeRepository;
import com.hendisantika.hris.springboothrissample1.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;
    private final JdbcTemplate jdbcTemplate;

    @Bean
    @Transactional
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if data already exists
            if (employeeRepository.count() > 0) {
                System.out.println("✓ Database already contains " + employeeRepository.count() + " employees - skipping data load");
                return;
            }

            System.out.println("Loading sample data using JPA repositories...");

            // Create Jobs using JDBC (since Job doesn't have @GeneratedValue)
            createJobs();

            // Create Departments
            Department engineering = createDepartment("Engineering");
            Department sales = createDepartment("Sales");
            Department hr = createDepartment("Human Resources");
            Department finance = createDepartment("Finance");
            Department marketing = createDepartment("Marketing");

            System.out.println("✓ Created " + departmentRepository.count() + " departments");

            // ONE PIECE Characters (25)
            createEmployee("Monkey D.", "Luffy", "luffy@grandline.com", "+81-3-1001", new BigDecimal("250000"), "CEO", null, engineering);
            createEmployee("Roronoa", "Zoro", "zoro@grandline.com", "+81-3-1002", new BigDecimal("180000"), "CTO", null, engineering);
            createEmployee("Nami", "Navigator", "nami@grandline.com", "+81-3-1003", new BigDecimal("180000"), "CFO", null, finance);
            createEmployee("Vinsmoke", "Sanji", "sanji@grandline.com", "+81-3-1004", new BigDecimal("150000"), "VP_ENG", null, engineering);
            createEmployee("Nico", "Robin", "robin@grandline.com", "+81-3-1005", new BigDecimal("150000"), "PRODMGR", null, engineering);
            createEmployee("Tony Tony", "Chopper", "chopper@grandline.com", "+81-3-1006", new BigDecimal("85000"), "SWENG", null, engineering);
            createEmployee("", "Franky", "franky@grandline.com", "+81-3-1007", new BigDecimal("120000"), "SENENG", null, engineering);
            createEmployee("", "Usopp", "usopp@grandline.com", "+81-3-1008", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("", "Jinbe", "jinbe@grandline.com", "+81-3-1009", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("Brook", "Soul King", "brook@grandline.com", "+81-3-1010", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Portgas D.", "Ace", "ace@grandline.com", "+81-3-1011", new BigDecimal("160000"), "VP_ENG", null, engineering);
            createEmployee("", "Sabo", "sabo@grandline.com", "+81-3-1012", new BigDecimal("155000"), "VP_ENG", null, engineering);
            createEmployee("Trafalgar", "Law", "law@grandline.com", "+81-3-1013", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("Eustass", "Kid", "kid@grandline.com", "+81-3-1014", new BigDecimal("135000"), "SENENG", null, engineering);
            createEmployee("", "Marco", "marco@grandline.com", "+81-3-1015", new BigDecimal("145000"), "SENENG", null, engineering);
            createEmployee("Edward", "Whitebeard", "whitebeard@grandline.com", "+81-3-1016", new BigDecimal("200000"), "CTO", null, engineering);
            createEmployee("Dracule", "Mihawk", "mihawk@grandline.com", "+81-3-1017", new BigDecimal("175000"), "VP_ENG", null, engineering);
            createEmployee("Donquixote", "Doflamingo", "doflamingo@grandline.com", "+81-3-1018", new BigDecimal("130000"), "SENENG", null, engineering);
            createEmployee("Charlotte", "Katakuri", "katakuri@grandline.com", "+81-3-1019", new BigDecimal("125000"), "SENENG", null, engineering);
            createEmployee("Charlotte", "Big Mom", "bigmom@grandline.com", "+81-3-1020", new BigDecimal("190000"), "CTO", null, engineering);
            createEmployee("", "Kaido", "kaido@grandline.com", "+81-3-1021", new BigDecimal("195000"), "CTO", null, engineering);
            createEmployee("Shanks", "Red Hair", "shanks@grandline.com", "+81-3-1022", new BigDecimal("150000"), "HRMAN", null, hr);
            createEmployee("Boa", "Hancock", "hancock@grandline.com", "+81-3-1023", new BigDecimal("85000"), "SALREP", null, sales);
            createEmployee("Buggy", "Clown", "buggy@grandline.com", "+81-3-1024", new BigDecimal("75000"), "MKTSPEC", null, marketing);
            createEmployee("", "Crocodile", "crocodile@grandline.com", "+81-3-1025", new BigDecimal("80000"), "ACCNT", null, finance);

            // NARUTO Characters (25)
            createEmployee("Naruto", "Uzumaki", "naruto@konoha.com", "+81-75-2001", new BigDecimal("145000"), "SENENG", null, engineering);
            createEmployee("Sasuke", "Uchiha", "sasuke@konoha.com", "+81-75-2002", new BigDecimal("145000"), "SENENG", null, engineering);
            createEmployee("Sakura", "Haruno", "sakura@konoha.com", "+81-75-2003", new BigDecimal("130000"), "PRODMGR", null, hr);
            createEmployee("Kakashi", "Hatake", "kakashi@konoha.com", "+81-75-2004", new BigDecimal("160000"), "VP_ENG", null, engineering);
            createEmployee("Hinata", "Hyuga", "hinata@konoha.com", "+81-75-2005", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Rock", "Lee", "rocklee@konoha.com", "+81-75-2006", new BigDecimal("100000"), "SWENG", null, engineering);
            createEmployee("Neji", "Hyuga", "neji@konoha.com", "+81-75-2007", new BigDecimal("110000"), "SENENG", null, engineering);
            createEmployee("", "Tenten", "tenten@konoha.com", "+81-75-2008", new BigDecimal("85000"), "SWENG", null, engineering);
            createEmployee("", "Gaara", "gaara@konoha.com", "+81-75-2009", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("", "Temari", "temari@konoha.com", "+81-75-2010", new BigDecimal("105000"), "SWENG", null, engineering);
            createEmployee("", "Kankuro", "kankuro@konoha.com", "+81-75-2011", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Shikamaru", "Nara", "shikamaru@konoha.com", "+81-75-2012", new BigDecimal("120000"), "PRODMGR", null, engineering);
            createEmployee("Ino", "Yamanaka", "ino@konoha.com", "+81-75-2013", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("Choji", "Akimichi", "choji@konoha.com", "+81-75-2014", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("", "Tsunade", "tsunade@konoha.com", "+81-75-2015", new BigDecimal("180000"), "CFO", null, finance);
            createEmployee("", "Jiraiya", "jiraiya@konoha.com", "+81-75-2016", new BigDecimal("165000"), "VP_ENG", null, engineering);
            createEmployee("", "Orochimaru", "orochimaru@konoha.com", "+81-75-2017", new BigDecimal("155000"), "VP_ENG", null, engineering);
            createEmployee("Itachi", "Uchiha", "itachi@konoha.com", "+81-75-2018", new BigDecimal("150000"), "SENENG", null, engineering);
            createEmployee("Kisame", "Hoshigaki", "kisame@konoha.com", "+81-75-2019", new BigDecimal("125000"), "SENENG", null, engineering);
            createEmployee("", "Deidara", "deidara@konoha.com", "+81-75-2020", new BigDecimal("115000"), "SENENG", null, engineering);
            createEmployee("", "Sasori", "sasori@konoha.com", "+81-75-2021", new BigDecimal("115000"), "SENENG", null, engineering);
            createEmployee("Minato", "Namikaze", "minato@konoha.com", "+81-75-2022", new BigDecimal("170000"), "VP_ENG", null, engineering);
            createEmployee("Kushina", "Uzumaki", "kushina@konoha.com", "+81-75-2023", new BigDecimal("130000"), "PRODMGR", null, hr);
            createEmployee("", "Obito", "obito@konoha.com", "+81-75-2024", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("Madara", "Uchiha", "madara@konoha.com", "+81-75-2025", new BigDecimal("185000"), "CTO", null, engineering);

            // DEMON SLAYER Characters (25)
            createEmployee("Tanjiro", "Kamado", "tanjiro@demonslayer.com", "+81-3-3001", new BigDecimal("100000"), "SWENG", null, engineering);
            createEmployee("Nezuko", "Kamado", "nezuko@demonslayer.com", "+81-3-3002", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Zenitsu", "Agatsuma", "zenitsu@demonslayer.com", "+81-3-3003", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("Inosuke", "Hashibira", "inosuke@demonslayer.com", "+81-3-3004", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("Giyu", "Tomioka", "giyu@demonslayer.com", "+81-3-3005", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("Shinobu", "Kocho", "shinobu@demonslayer.com", "+81-3-3006", new BigDecimal("135000"), "SENENG", null, engineering);
            createEmployee("Kyojuro", "Rengoku", "kyojuro@demonslayer.com", "+81-3-3007", new BigDecimal("145000"), "SENENG", null, engineering);
            createEmployee("Tengen", "Uzui", "tengen@demonslayer.com", "+81-3-3008", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("Muichiro", "Tokito", "muichiro@demonslayer.com", "+81-3-3009", new BigDecimal("125000"), "SENENG", null, engineering);
            createEmployee("Mitsuri", "Kanroji", "mitsuri@demonslayer.com", "+81-3-3010", new BigDecimal("130000"), "SENENG", null, engineering);
            createEmployee("Obanai", "Iguro", "obanai@demonslayer.com", "+81-3-3011", new BigDecimal("130000"), "SENENG", null, engineering);
            createEmployee("Gyomei", "Himejima", "gyomei@demonslayer.com", "+81-3-3012", new BigDecimal("150000"), "VP_ENG", null, engineering);
            createEmployee("Sanemi", "Shinazugawa", "sanemi@demonslayer.com", "+81-3-3013", new BigDecimal("135000"), "SENENG", null, engineering);
            createEmployee("Kanao", "Tsuyuri", "kanao@demonslayer.com", "+81-3-3014", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Genya", "Shinazugawa", "genya@demonslayer.com", "+81-3-3015", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("Muzan", "Kibutsuji", "muzan@demonslayer.com", "+81-3-3016", new BigDecimal("200000"), "CTO", null, engineering);
            createEmployee("", "Kokushibo", "kokushibo@demonslayer.com", "+81-3-3017", new BigDecimal("160000"), "VP_ENG", null, engineering);
            createEmployee("", "Doma", "doma@demonslayer.com", "+81-3-3018", new BigDecimal("155000"), "VP_ENG", null, engineering);
            createEmployee("", "Akaza", "akaza@demonslayer.com", "+81-3-3019", new BigDecimal("150000"), "VP_ENG", null, engineering);
            createEmployee("", "Hantengu", "hantengu@demonslayer.com", "+81-3-3020", new BigDecimal("120000"), "SENENG", null, engineering);
            createEmployee("", "Gyokko", "gyokko@demonslayer.com", "+81-3-3021", new BigDecimal("115000"), "SENENG", null, engineering);
            createEmployee("", "Daki", "daki@demonslayer.com", "+81-3-3022", new BigDecimal("85000"), "SALREP", null, sales);
            createEmployee("", "Gyutaro", "gyutaro@demonslayer.com", "+81-3-3023", new BigDecimal("110000"), "SENENG", null, engineering);
            createEmployee("", "Enmu", "enmu@demonslayer.com", "+81-3-3024", new BigDecimal("80000"), "MKTSPEC", null, marketing);
            createEmployee("", "Kaigaku", "kaigaku@demonslayer.com", "+81-3-3025", new BigDecimal("100000"), "SWENG", null, engineering);

            // JUJUTSU KAISEN Characters (25)
            createEmployee("Yuji", "Itadori", "yuji@jujutsu.com", "+81-3-4001", new BigDecimal("85000"), "SWENG", null, engineering);
            createEmployee("Megumi", "Fushiguro", "megumi@jujutsu.com", "+81-3-4002", new BigDecimal("85000"), "SWENG", null, engineering);
            createEmployee("Nobara", "Kugisaki", "nobara@jujutsu.com", "+81-3-4003", new BigDecimal("85000"), "SWENG", null, engineering);
            createEmployee("Satoru", "Gojo", "gojo@jujutsu.com", "+81-3-4004", new BigDecimal("200000"), "CTO", null, engineering);
            createEmployee("Maki", "Zenin", "maki@jujutsu.com", "+81-3-4005", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Toge", "Inumaki", "toge@jujutsu.com", "+81-3-4006", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("", "Panda", "panda@jujutsu.com", "+81-3-4007", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("Yuta", "Okkotsu", "yuta@jujutsu.com", "+81-3-4008", new BigDecimal("140000"), "SENENG", null, engineering);
            createEmployee("Kento", "Nanami", "nanami@jujutsu.com", "+81-3-4009", new BigDecimal("150000"), "VP_ENG", null, engineering);
            createEmployee("Suguru", "Geto", "geto@jujutsu.com", "+81-3-4010", new BigDecimal("145000"), "SENENG", null, engineering);
            createEmployee("", "Mahito", "mahito@jujutsu.com", "+81-3-4011", new BigDecimal("130000"), "SENENG", null, engineering);
            createEmployee("", "Jogo", "jogo@jujutsu.com", "+81-3-4012", new BigDecimal("125000"), "SENENG", null, engineering);
            createEmployee("", "Hanami", "hanami@jujutsu.com", "+81-3-4013", new BigDecimal("120000"), "SENENG", null, engineering);
            createEmployee("Ryomen", "Sukuna", "sukuna@jujutsu.com", "+81-3-4014", new BigDecimal("190000"), "CTO", null, engineering);
            createEmployee("Aoi", "Todo", "todo@jujutsu.com", "+81-3-4015", new BigDecimal("110000"), "SENENG", null, engineering);
            createEmployee("Mai", "Zenin", "mai@jujutsu.com", "+81-3-4016", new BigDecimal("85000"), "SWENG", null, engineering);
            createEmployee("Noritoshi", "Kamo", "noritoshi@jujutsu.com", "+81-3-4017", new BigDecimal("95000"), "SWENG", null, engineering);
            createEmployee("Momo", "Nishimiya", "momo@jujutsu.com", "+81-3-4018", new BigDecimal("80000"), "SWENG", null, engineering);
            createEmployee("", "Mechamaru", "mechamaru@jujutsu.com", "+81-3-4019", new BigDecimal("90000"), "SWENG", null, engineering);
            createEmployee("Kasumi", "Miwa", "miwa@jujutsu.com", "+81-3-4020", new BigDecimal("75000"), "SWENG", null, engineering);
            createEmployee("", "Utahime", "utahime@jujutsu.com", "+81-3-4021", new BigDecimal("120000"), "PRODMGR", null, hr);
            createEmployee("Mei", "Mei", "meimei@jujutsu.com", "+81-3-4022", new BigDecimal("115000"), "PRODMGR", null, hr);
            createEmployee("", "Choso", "choso@jujutsu.com", "+81-3-4023", new BigDecimal("105000"), "SWENG", null, engineering);
            createEmployee("", "Eso", "eso@jujutsu.com", "+81-3-4024", new BigDecimal("70000"), "ACCNT", null, finance);
            createEmployee("", "Kechizu", "kechizu@jujutsu.com", "+81-3-4025", new BigDecimal("70000"), "ACCNT", null, finance);

            long count = employeeRepository.count();
            System.out.println("✓ Successfully created " + count + " employees using JPA repositories!");
            System.out.println("✓ Sample employee: " + employeeRepository.findAll().iterator().next().getFirstName());
        };
    }

    private void createJobs() {
        // Create jobs using JPA repository so they're managed entities
        createJob("CEO", "Chief Executive Officer", new BigDecimal("200000.00"), new BigDecimal("500000.00"));
        createJob("CTO", "Chief Technology Officer", new BigDecimal("150000.00"), new BigDecimal("350000.00"));
        createJob("CFO", "Chief Financial Officer", new BigDecimal("150000.00"), new BigDecimal("350000.00"));
        createJob("VP_ENG", "VP of Engineering", new BigDecimal("120000.00"), new BigDecimal("250000.00"));
        createJob("VP_SALES", "VP of Sales", new BigDecimal("120000.00"), new BigDecimal("250000.00"));
        createJob("SENENG", "Senior Software Engineer", new BigDecimal("90000.00"), new BigDecimal("180000.00"));
        createJob("SWENG", "Software Engineer", new BigDecimal("70000.00"), new BigDecimal("130000.00"));
        createJob("PRODMGR", "Product Manager", new BigDecimal("90000.00"), new BigDecimal("180000.00"));
        createJob("HRMAN", "HR Manager", new BigDecimal("70000.00"), new BigDecimal("130000.00"));
        createJob("SALREP", "Sales Representative", new BigDecimal("50000.00"), new BigDecimal("100000.00"));
        createJob("MKTSPEC", "Marketing Specialist", new BigDecimal("55000.00"), new BigDecimal("95000.00"));
        createJob("ACCNT", "Accountant", new BigDecimal("55000.00"), new BigDecimal("95000.00"));
        System.out.println("✓ Created 12 job types");

        // Create users via JDBC (User entity doesn't have repository in DataLoader)
        jdbcTemplate.execute("INSERT INTO HR_USERS (USERNAME, PASSWORD, EMAIL, FULL_NAME, ROLE, ACTIVE) VALUES ('admin', 'admin123', 'admin@hris.com', 'System Administrator', 'ADMIN', true)");
        jdbcTemplate.execute("INSERT INTO HR_USERS (USERNAME, PASSWORD, EMAIL, FULL_NAME, ROLE, ACTIVE) VALUES ('user', 'user123', 'user@hris.com', 'Regular User', 'USER', true)");
        jdbcTemplate.execute("INSERT INTO HR_USERS (USERNAME, PASSWORD, EMAIL, FULL_NAME, ROLE, ACTIVE) VALUES ('hendi', 'password', 'hendisantika@gmail.com', 'Hendi Santika', 'ADMIN', true)");
        System.out.println("✓ Created 3 users");
    }

    private void createJob(String jobId, String jobTitle, BigDecimal minSalary, BigDecimal maxSalary) {
        Job job = new Job();
        job.setJobId(jobId);
        job.setJobTitle(jobTitle);
        job.setMinSalary(minSalary);
        job.setMaxSalary(maxSalary);
        jobRepository.save(job);
    }

    private Department createDepartment(String name) {
        Department dept = new Department();
        dept.setDepartmentName(name);
        return departmentRepository.save(dept);
    }

    private void createEmployee(String firstName, String lastName, String email, String phone,
                                BigDecimal salary, String jobId, Employee manager, Department department) {
        Employee emp = new Employee();
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setEmail(email);
        emp.setPhoneNumber(phone);
        emp.setHireDate(new Date());
        emp.setSalary(salary);
        emp.setManager(manager);
        emp.setDepartment(department);

        // Fetch existing job from database using JPA repository (managed entity)
        Job job = jobRepository.findById(jobId).orElse(null);
        emp.setJob(job);

        employeeRepository.save(emp);
    }
}
