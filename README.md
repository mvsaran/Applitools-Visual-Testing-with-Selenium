# 👀 Applitools Visual Testing with Selenium (Java)

A hands‑on, beginner‑friendly project that shows how to do **Visual UI Regression Testing** using **Applitools Eyes** + **Selenium WebDriver**.  
You’ll learn how to create a visual baseline, compare new runs, and review diffs in the Applitools Test Manager.

---

## 🧠 Why Visual Testing? (In simple words)

Functional tests tell you **if** something works. Visual tests tell you **how it looks** to the user.

Visual testing catches issues such as:
- Misaligned or hidden buttons/labels
- CSS or theme breaks after a deployment
- Incorrect fonts, colors, spacing, or layout shifts
- Images/icons missing or overlapping
- Responsive (mobile/desktop) rendering problems

**Applitools** uses Visual AI to detect only meaningful differences (reducing flaky pixel-level false positives) and provides a dashboard to approve/reject changes.

---

## 📌 What this project does (Use case)

We use the **Applitools Hello World demo** page to:
1. Navigate to the page and take a **baseline** snapshot.
2. Re-run with intentional changes (add `?diff1` / `?diff2` in the URL) to create **visual differences**.
3. Let Applitools highlight what changed so you can **accept** (baseline update) or **reject** (bug) the new snapshot.

> Tip: This mirrors real-world UI checks after CSS/JS upgrades.

---

## ✅ Prerequisites

1. **Java JDK 21+** installed  
   - Check: `java -version`
2. **Maven** installed  
   - Check: `mvn -v`
3. **Git** installed  
   - Check: `git --version`
4. **IDE** (IntelliJ / Eclipse) – optional but recommended
5. **Applitools account (free)**  
   - Sign up: https://auth.applitools.com/users/register  
   - After login: click your avatar → **My API Key** → copy the key
6. **Set your Applitools API key as an environment variable**  
   - **Windows (PowerShell):**
     ```powershell
     setx APPLITOOLS_API_KEY "YOUR_API_KEY"
     ```
     Close & reopen the terminal to load it.
   - **macOS/Linux (bash/zsh):**
     ```bash
     export APPLITOOLS_API_KEY="YOUR_API_KEY"
     ```
   - Verify it’s available:
     ```bash
     echo %APPLITOOLS_API_KEY%   # Windows
     echo $APPLITOOLS_API_KEY    # macOS/Linux
     ```

---

## 🗂️ Project structure (typical Maven layout)

```
Applitools-Visual-Testing-with-Selenium/
├─ src/
│  ├─ main/java/           # (not used in this simple demo)
│  ├─ main/resources/      # (not used in this simple demo)
│  └─ test/java/
│     └─ VisualTesting/
│        └─ Applitest.java  # your test class
├─ pom.xml
└─ README.md
```

> If you use WebDriverManager, your test can start Chrome without manually downloading drivers:
> ```java
> WebDriverManager.chromedriver().setup();
> WebDriver driver = new ChromeDriver();
> ```

---

## 📦 Maven dependencies (paste into `pom.xml`)

> **Note:** Do not keep duplicate Selenium dependencies. If you have both 4.31.0 and 4.35.0, **keep only 4.35.0**.

```xml
<dependencies> 
    <!-- ✅ Selenium -->
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.31.0</version>
    </dependency>

    <!-- ✅ TestNG -->
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>7.11.0</version>
      <scope>test</scope>
    </dependency>

    <!-- ✅ WebDriverManager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>6.2.0</version>
    </dependency>

    <!-- ✅ Applitools Eyes -->
    <dependency>
        <groupId>com.applitools</groupId>
        <artifactId>eyes-selenium-java5</artifactId>
        <version>5.0.2</version>
    </dependency>

    <!-- ✅ JUnit 5 (optional; use if you run JUnit tests) -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.13.4</version>
        <scope>test</scope>
    </dependency>

    <!-- ❗ Duplicate Selenium (prefer keeping this newer one only) -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.35.0</version>
    </dependency>

    <!-- ✅ AssertJ (fluent assertions for unit tests) -->
    <dependency>
      <groupId>org.assertj</groupId>
      <artifactId>assertj-core</artifactId>
      <version>3.24.2</version>
      <scope>test</scope>
    </dependency>
</dependencies>
```

**Recommended cleanup:** keep only one Selenium dependency (the latest: `4.35.0`).

---

## 🏁 Quick start (copy–paste)

```bash
# 1) Clone
git clone https://github.com/mvsaran/Applitools-Visual-Testing-with-Selenium.git
cd Applitools-Visual-Testing-with-Selenium

# 2) Build
mvn -q -DskipTests clean install

# 3) Run a specific test class (TestNG or JUnit @Test)
mvn -Dtest=Applitest test
```

If using an IDE:
- Right‑click `Applitest.java` → **Run**

---

## 🧪 What the test typically looks like

> This is the minimal flow your `Applitest.java` should perform:

```java
// Open browser → go to demo site
driver.get("https://applitools.com/helloworld/");

// Initialize Eyes & open test
eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
eyes.open(driver, "Applitools Demo", "Hello World Test");

// Take a visual checkpoint
eyes.checkWindow("Hello World");

// (Optional) Navigate with differences and take another checkpoint
driver.get("https://applitools.com/helloworld/?diff1"); // or ?diff2
eyes.checkWindow("Hello World with diffs");

// Close Eyes (sends results)
eyes.close();
```

---

## 🔍 After the run: review visual diffs

1. Open **Applitools Test Manager** (you’ll see your test run).  
2. Compare **Baseline** vs **Checkpoint** side by side.  
3. **Accept** (update baseline) or **Reject** (mark as bug) the changes.  
4. Add ignore/layout regions if needed to handle dynamic areas.

---

## 🧰 Useful scripts

### Run in headless mode (if your code supports it)
```java
ChromeOptions opts = new ChromeOptions();
opts.addArguments("--headless=new");
WebDriver driver = new ChromeDriver(opts);
```

### Run with Maven & TestNG suite file (optional)
```bash
mvn -Dsurefire.suiteXmlFiles=testng.xml test
```

---

## 🧯 Troubleshooting

- **`Eyes API key is missing`** → Ensure `APPLITOOLS_API_KEY` is set in your environment (reopen terminal after `setx` on Windows).
- **Driver errors** → Use **WebDriverManager** and call `WebDriverManager.chromedriver().setup();` before creating the driver.
- **Nothing appears in Applitools** → Make sure you called `eyes.open(...)`, `eyes.check...`, and `eyes.close()`.
- **Duplicate dependencies** → Keep **one** `selenium-java` version (recommended: `4.35.0`).

---

## 🤖 CI/CD (optional quick example – GitHub Actions)

Create `.github/workflows/visual.yml`:

```yaml
name: visual-tests
on: [push, pull_request]
jobs:
  run:
    runs-on: ubuntu-latest
    env:
      APPLITOOLS_API_KEY: ${{ secrets.APPLITOOLS_API_KEY }}
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "21"
      - name: Build & test
        run: mvn -Dtest=Applitest test
```

Then add your API key in **Repo → Settings → Secrets → Actions → New secret**.

---

## 📚 Further learning

- Applitools Docs: https://applitools.com/docs/
- Selenium Docs: https://www.selenium.dev/documentation/

---

## 📝 License

MIT (feel free to reuse and modify).
