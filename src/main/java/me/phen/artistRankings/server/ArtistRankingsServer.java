// (K) ALL RIGHTS REVERSED - Reprint what you like

package me.phen.artistRankings.server;

import io.dropwizard.Application;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import me.phen.artistRankings.server.exception.ExceptionHandler;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 * Run the thing.
 */
public class ArtistRankingsServer extends Application<ServerConfiguration> {

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
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        //bootstrap.addCommand(new RenderCommand())
        //bootstrap.addBundle(new AssetsBundle("/assets/api-front-end", ""))
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws ClassNotFoundException {
        registerSwagger(environment);
        registerJerseyResources(configuration, environment.jersey());
    }

    private void registerJerseyResources(ServerConfiguration configuration, JerseyEnvironment environment) {
        environment.register(ExceptionHandler.class);

//        environment.getResourceConfig().getContainerRequestFilters().add(new CustomLoggingFilter());
//        environment.getResourceConfig().getContainerResponseFilters().add(new CustomLoggingFilter());
    }

    private void registerSwagger(Environment environment) {
        environment.jersey().register(new ApiListingResource());
        environment.jersey().register(new SwaggerSerializers());

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.1");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/");
        beanConfig.setResourcePackage("me.phen.artistRankings.server.resource");
        beanConfig.setScan(true);

        //Configure CORS for Swagger-UI Server

        environment.servlets()
                .addFilter("cross-origins-filter", CrossOriginFilter.class)
                .addMappingForUrlPatterns(null, false, "/*");
    }
}
