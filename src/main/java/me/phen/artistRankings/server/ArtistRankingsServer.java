// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server;

import io.dropwizard.core.Application;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.swagger.v3.jaxrs2.SwaggerSerializers;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import me.phen.artistRankings.ArtistRankingsGuiceModule;
import me.phen.artistRankings.server.exception.ExceptionHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Run the thing.
 */
public class ArtistRankingsServer extends Application<Configuration> {

    //If no args, use these as a default
    private static final String[] defaultArgs = new String[] {"server", "./src/main/resources/artist-rankings.yml"};

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            args = defaultArgs;
        }

        new ArtistRankingsServer().run(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        GuiceBundle guiceBundle = GuiceBundle.builder().modules(new ArtistRankingsGuiceModule()).build();
        bootstrap.addBundle(guiceBundle);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void run(Configuration configuration, Environment environment) {
        registerSwagger(environment);
        registerJerseyResources(configuration, environment.jersey());
    }

    private void registerJerseyResources(Configuration configuration, JerseyEnvironment jersey) {
        jersey.register(ExceptionHandler.class);
        jersey.packages(getClass().getPackage().getName());

        jersey.register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY, Integer.MAX_VALUE));

//        jersey.getResourceConfig().getContainerRequestFilters().add(new CustomLoggingFilter());
//        jersey.getResourceConfig().getContainerResponseFilters().add(new CustomLoggingFilter());
    }

    private void registerSwagger(Environment environment) {
//        environment.jersey().register(new SwaggerSerializers());

        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                .openAPI(new OpenAPI())
                .prettyPrint(true)
                .resourcePackages(Stream.of("me.phen.artistRankings.server.resource").collect(Collectors.toSet()));

        environment.jersey().register(oasConfig);

        //Configure CORS for Swagger-UI Server
        environment.servlets()
                .addFilter("cross-origins-filter", CrossOriginFilter.class)
                .addMappingForUrlPatterns(null, false, "/*");
    }
}
