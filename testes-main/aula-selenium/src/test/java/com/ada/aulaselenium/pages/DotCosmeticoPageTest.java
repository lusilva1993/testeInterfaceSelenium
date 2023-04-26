package com.ada.aulaselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

public class DotCosmeticoPageTest {

    ChromeDriver chrome;
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\lucia\\IdeaProjects\\testes-main\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.dotcosmeticos.com.br/");
    }

    @BeforeEach
    public void beforeEach(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        chrome = new ChromeDriver(options);
    }

    @Test
    public void testandoLogin(){

        WebElement botaoLogin = driver.findElement(By.xpath("//a[@title='Login']"));
        botaoLogin.click();

        // Informa o e-mail
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("bruce@teste.com");

        // Senha
        WebElement senha = driver.findElement(By.id("pass"));
        senha.sendKeys("testando123");

        // Clica no botão para logar
        WebElement botaoLoginClick = driver.findElement(By.id("send2"));
        botaoLoginClick.click();
    }

    @Test
    public void testeCadastro() {

        WebElement linkRegistro = driver.findElement(By.xpath("//a[@title='Minha Conta']"));
        linkRegistro.click();

        WebElement botaoCriacaoConta = driver.findElement(By.xpath("//button[@title='Criar Conta']"));
        botaoCriacaoConta.click();

        WebElement primeiroNome = driver.findElement(By.id("firstname"));
        primeiroNome.sendKeys("Bruce");

        WebElement ultimoNome = driver.findElement(By.id("lastname"));
        ultimoNome.sendKeys("Wayne");

        WebElement email = driver.findElement(By.id("email_address"));
        email.sendKeys("bruce.wayne@gmail.com");

        WebElement senha = driver.findElement(By.id("password"));
        senha.sendKeys("testando123");

        WebElement confirmarSenha = driver.findElement(By.id("password-confirmation"));
        confirmarSenha.sendKeys("testando123");

        //WebElement botaoCriacaoConta = driver.findElement(By.xpath("//button[@title='Criar Conta']"));
        //botaoCriacaoConta.click();

        WebElement mensagemSucesso = driver.findElement(By.xpath("//div[@class='success-msg']"));
        Assert.assertEquals(mensagemSucesso.getText(), "Obrigado por se registrar com a Dot Cosméticos.");
    }

    @Test
    public void testandoBuscaSimplificada() {

        // Encontra o campo de busca e insere o que vamos pesquisar
        WebElement inserePesquisaNoCampoDeBusca = driver.findElement(By.id("search"));
        inserePesquisaNoCampoDeBusca .sendKeys("progressiva");

        // Faz a ação de clicar no botão de busca
        WebElement clicaBotaoDeBusca = driver.findElement(By.xpath("//button[@class='action search']"));
        clicaBotaoDeBusca.click();

        // Confirma se a página foi carregada com os resultados da busca
        WebElement procuraResultados = driver.findElement(By.xpath("//h1[contains(text(),'Resultados de busca para:')]"));
        Assert.assertTrue(procuraResultados.isDisplayed());

        // Checa se os resultados de busca estão corretos (se possuem o termo de pesquisa)
        WebElement procuraResultadoPesquisa = driver.findElement(By.xpath("//span[contains(text(),'shampoo')]"));
        Assert.assertTrue(procuraResultadoPesquisa.isDisplayed());
    }

    @Test
    public void testandoBuscaAvancada() {

        // Click do botão de busca avançada
        WebElement botaoBuscaAvancada = driver.findElement(By.xpath("//a[@title='Busca Avançada']"));
        botaoBuscaAvancada.click();

        // Encontra o campo e insere o termo a pesquisar
        WebElement campoPesquisa = driver.findElement(By.id("search"));
        campoPesquisa.sendKeys("shampoo");

        // Seleciona a categoria "Cabelos"
        WebElement selecionaCategoria = driver.findElement(By.xpath("//label[@for='category_id_25']"));
        selecionaCategoria.click();

        // Exemplo, selecionando a marca "L'Oreal Professionnel"
        WebElement selecionandoMarca = driver.findElement(By.xpath("//label[@for='brand_id_3']"));
        selecionandoMarca.click();

        // Click do botão de busca
        WebElement botaoBusca = driver.findElement(By.xpath("//button[@class='action advanced-search']"));
        botaoBusca.click();

        // Confirma se pagina com os resultados foi carregada
        WebElement resultadoBusca = driver.findElement(By.xpath("//h1[contains(text(),'Resultados de busca para:')]"));
        Assert.assertTrue(resultadoBusca.isDisplayed());

        // Confirma se a busca possui o termo pesquisado, a categoria e a marca selecionadas
        WebElement buscaTermoPesquisado = driver.findElement(By.xpath("//span[contains(text(),'shampoo')]"));
        Assert.assertTrue(buscaTermoPesquisado.isDisplayed());

        WebElement buscaCategoria = driver.findElement(By.xpath("//a[contains(text(),'Cabelos')]"));
        Assert.assertTrue(buscaCategoria.isDisplayed());

        WebElement buscaMarcaPesquisada = driver.findElement(By.xpath("//a[contains(text(),\"L'Oreal Professionnel\")]"));
        Assert.assertTrue(buscaMarcaPesquisada.isDisplayed());
    }
}
