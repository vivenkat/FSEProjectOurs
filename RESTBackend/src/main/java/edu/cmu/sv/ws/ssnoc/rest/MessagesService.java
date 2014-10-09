package edu.cmu.sv.ws.ssnoc.rest;

import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IMessageDAO;
import edu.cmu.sv.ws.ssnoc.data.po.MessagePO;
import edu.cmu.sv.ws.ssnoc.dto.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/messages")
public class MessagesService extends BaseService {

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/wall")
    public List<Message> getWallPosts () {
        List<Message> messages = null;

        try {
            List<MessagePO> messagePOs = DAOFactory.getInstance().getMessageDAO().loadWallMessages();

            messages = new ArrayList<Message>();
            for (MessagePO po : messagePOs) {
                Message dto = ConverterUtils.convert(po);
                messages.add(dto);
            }
        } catch (Exception e) {
            handleException(e);
        } finally {

        }

        return messages;
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{author}/{target}")
    public List<Message> getPMs (@PathParam("author") String author, @PathParam("target") String target) {
        List<Message> messages = null;

        try {
            List<MessagePO> messagePOs = DAOFactory.getInstance().getMessageDAO().loadPrivateMessages(author, target);

            messages = new ArrayList<Message>();
            for (MessagePO po : messagePOs) {
                Message dto = ConverterUtils.convert(po);
                messages.add(dto);
            }
        } catch (Exception e) {
            handleException(e);
        } finally {

        }

        return messages;
    }



}
