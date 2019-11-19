package br.com.movie.services;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import br.com.movie.controller.ProducerController;
import br.com.movie.model.AlreadyExistsProducerException;
import br.com.movie.model.Producer;

@Path("/producers/")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON })
public class ProducerServices {

	private static ProducerController producerController = new ProducerController();
	private static final String _MAX = "max";
	private static final String _MIN = "min";
	private static final String _PATH_NAME = "{name}";
	private static final String _NAME = "name";
	private static final String _PATH_PRODUCER_NAME = "\"producers/{name}\"";

	@GET
	public Map<String, List<Producer>> listProducerWithMinimumAndMaximumTwoPrizesInterval() throws Exception {
		Map<String, List<Producer>> restResponse = new HashMap<>();
		restResponse.put(_MIN, producerController.minIntervalOfAWinner());
		restResponse.put(_MAX, producerController.maxIntervalOfAWinner());
		return restResponse;
	}

	@GET
	@Path(_PATH_NAME)
	public List<Producer> getProducer(@PathParam(_NAME) String producerName) {
		List<Producer> producer = producerController.findProducer(producerName);
		if (producer == null || producer.isEmpty()) {
			throw new WebApplicationException(404);
		} else {
			return producer;
		}
	}
	
	@POST
	public Response createProducer(Producer producer) {
		try {
			producerController.addProducer(producer);
		} catch (AlreadyExistsProducerException e) {
			throw new WebApplicationException(Status.CONFLICT);
		}
		URI uri = UriBuilder.fromPath(_PATH_PRODUCER_NAME)
				.build(producer.getProducer());
		return Response.created(uri)
				.entity(producer)
				.build();
	}
	
	@PUT
	@Path(_PATH_NAME)
	public void updateProducer(@PathParam(_NAME) String producerName, Producer toUpdateproducer) {
		List<Producer> producer = producerController.findProducer(producerName);
		if (producer == null || producer.isEmpty()) {
			throw new WebApplicationException(404);
		} else {
			producerController.updateProducer(toUpdateproducer);
		}
	}
	
	@DELETE
	@Path(_PATH_NAME)
	public void deleteProducer(@PathParam(_NAME) String producerName) {
		producerController.removeProducer(producerName);
	}

}
