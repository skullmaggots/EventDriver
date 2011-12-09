import org.codehaus.groovy.control.CompilerConfiguration

def compilerConfiguration = new CompilerConfiguration()
compilerConfiguration.scriptBaseClass = "EventDriverBinding"
def parentClassLoader = Thread.currentThread().getContextClassLoader();
def scriptClassLoader = new GroovyClassLoader(parentClassLoader,compilerConfiguration);
def gse = new GroovyScriptEngine("./src",scriptClassLoader);
def scriptBinding = new Binding();
gse.run(args[0],scriptBinding);

