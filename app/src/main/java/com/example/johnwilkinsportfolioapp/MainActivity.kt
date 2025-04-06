package com.example.johnwilkinsportfolioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.johnwilkinsportfolioapp.ui.theme.JohnWilkinsPortfolioAppTheme

sealed class Screen(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Home : Screen("home", "Home", { Icon(Icons.Filled.Home, contentDescription = "Home") })
    object Education : Screen("education", "Education", { Icon(Icons.Filled.School, contentDescription = "Education") })
    object Skills : Screen("skills", "Skills", { Icon(Icons.Filled.Code, contentDescription = "Skills") })
    object Projects : Screen("projects", "Projects", { Icon(Icons.Filled.Work, contentDescription = "Projects") })
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JohnWilkinsPortfolioAppTheme {
                PortfolioApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioApp() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Home, Screen.Education, Screen.Skills, Screen.Projects)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("John Wilkins") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                actions = {
                    IconButton(onClick = { /* TODO: Add contact dialog */ }) {
                        Icon(Icons.Filled.Email, contentDescription = "Contact")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = { screen.icon() },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Education.route) { EducationScreen() }
            composable(Screen.Skills.route) { SkillsScreen() }
            composable(Screen.Projects.route) { ProjectsScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "I'm a Computer Science graduate student with experience in web development and software engineering.",
            style = MaterialTheme.typography.bodyLarge
        )
        // Contact information
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Contact Information",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text("Phone: (xxx) xxx-xxxx")
        Text("Email: xxxxxxxxxxxxxxxxxxxxx@gmail.com")
        Text("LinkedIn: linkedin.com/in/xxxxxxxxxxxxxxxxxxxx")
        Text("GitHub: github.com/xxxxxxxxxxxxxxxxx")
        Text("YouTube: youtube.com/@xxxxxxxxxxxxx")
    }
}

@Composable
fun EducationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // University of Southern Mississippi
        EducationItem(
            school = "University of Southern Mississippi",
            degree = "Master's degree in Computer Science",
            gpa = "GPA 3.43/4.00",
            location = "Hattiesburg, MS",
            date = "Jan. 2024-May 2025",
            coursework = "Information Security and Risk Mgmt, Data Structures & Algorithms, Database Management"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Mississippi Gulf Coast Community College
        EducationItem(
            school = "Mississippi Gulf Coast Community College",
            degree = "Non-degree seeking",
            gpa = "GPA 3.68/4.00",
            location = "Gulfport, MS",
            date = "Aug. 2023-Dec. 2023",
            coursework = "Web development with HTML & CSS, C# Programming, SQL Programming"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // University of Mississippi
        EducationItem(
            school = "University of Mississippi",
            degree = "Bachelor of Science in Legal Studies",
            gpa = "GPA 3.71/4.00",
            location = "Oxford, MS",
            date = "Aug. 2015-May 2019",
            coursework = "Introduction to Java"
        )
    }
}

@Composable
fun EducationItem(
    school: String,
    degree: String,
    gpa: String,
    location: String,
    date: String,
    coursework: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = school,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = degree,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = gpa,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = location,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Relevant Coursework:",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = coursework,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun SkillsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SkillCategory(
            title = "Languages",
            skills = listOf("JavaScript", "SQL", "TypeScript")
        )
        
        SkillCategory(
            title = "Frameworks",
            skills = listOf("Next.js", "React.js", "MongoDB", "Express.js", "Node.js")
        )
        
        SkillCategory(
            title = "Developer Tools",
            skills = listOf("Git", "GitHub", "VS Code")
        )
        
        SkillCategory(
            title = "Cloud Computing",
            skills = listOf("AWS")
        )
    }
}

@Composable
fun SkillCategory(title: String, skills: List<String>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            skills.forEach { skill ->
                Text(
                    text = "• $skill",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun ProjectsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProjectItem(
            title = "Shopping Cart",
            technologies = "TypeScript, React.js",
            githubUrl = "https://github.com/wilkinsjohnstanley/typescript-shoppingcart",
            description = listOf(
                "Implemented a responsive frontend with React.js",
                "Ensured type safety by using TypeScript",
                "Made use of React Hooks such as useState and useEffect"
            )
        )
        
        ProjectItem(
            title = "Metromart",
            technologies = "SQL, React.js",
            githubUrl = "https://github.com/wilkinsjohnstanley/metromart",
            description = listOf(
                "Implemented the use of a SQL Server to query ecommerce data",
                "Created a User Interface using React.js"
            )
        )
        
        ProjectItem(
            title = "Cryptopals Testing Suite",
            technologies = "Next.js 14",
            githubUrl = "https://github.com/Streudal/cryptopals",
            description = listOf(
                "Worked collaboratively with a group of four using GitHub",
                "Exalidraw for sketching the User Interface design",
                "Implemented Dynamic Routing",
                "Implemented testing with Vitest"
            )
        )
    }
}

@Composable
fun ProjectItem(
    title: String,
    technologies: String,
    githubUrl: String,
    description: List<String>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = technologies,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = githubUrl,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            description.forEach { point ->
                Text(
                    text = "• $point",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
