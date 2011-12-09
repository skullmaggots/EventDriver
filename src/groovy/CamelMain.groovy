import org.apache.camel.impl.MainSupport
import org.apache.camel.ProducerTemplate
import org.apache.camel.CamelContext
import org.apache.camel.view.ModelFileGenerator
import org.apache.camel.CamelAuthorizationException
import org.apache.camel.impl.DefaultCamelContext

class CamelMain extends MainSupport {

    CamelContext ctx = new DefaultCamelContext()

    @Override
    protected ProducerTemplate findOrCreateCamelTemplate() {
        return ctx.createProducerTemplate()
    }

    @Override
    protected Map<String, CamelContext> getCamelContextMap() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected ModelFileGenerator createModelFileGenerator() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}
