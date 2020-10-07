package io.serverlessworkflow.rest.diagram;

import io.serverlessworkflow.api.interfaces.WorkflowDiagram;
import io.serverlessworkflow.diagram.WorkflowDiagramImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/swdiagram")
public class DiagramResource {

    @POST
    @Produces(MediaType.APPLICATION_SVG_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public String fromJson(String source) {
        return produceSVG(source);
    }

    @POST
    @Produces(MediaType.APPLICATION_SVG_XML)
    @Consumes("text/x-yaml")
    public String fromYaml(String source) {
        return produceSVG(source);
    }

    private String produceSVG(String source) {
        try {
            WorkflowDiagram workflowDiagram = new WorkflowDiagramImpl();
            workflowDiagram.setSource(source);

            return workflowDiagram.getSvgDiagram();
        } catch (Exception e) {
            return "<svg height=\"50\" width=\"500\">\n" +
                    "  <text x=\"0\" y=\"15\" fill=\"red\">" + e.getMessage() + "</text>\n" +
                    "</svg>";
        }
    }
}