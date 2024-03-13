package com.newrelic.labs.webmethods.tn;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.softwareag.app.tn.apigateway.APIGatewayConstants.Type;
import com.softwareag.app.tn.apigateway.APIInvocationSearchDocument;
import com.softwareag.app.tn.apigateway.APISearchDocument;
import com.softwareag.app.tn.apigateway.APISearchField;
import com.softwareag.app.tn.util.ws.IWebService;
import com.wm.app.tn.data.TuplePair;
import com.wm.app.tn.db.DeliveryJobQuery;
import com.wm.app.tn.db.EventQuery;
import com.wm.app.tn.db.InviteQuery;
import com.wm.app.tn.db.SimpleDocQuery;
import com.wm.app.tn.delivery.DeliveryService;
import com.wm.app.tn.delivery.GuaranteedJob;
import com.wm.app.tn.doc.BizDocContentPart;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.app.tn.onboarding.Invite;
import com.wm.data.IData;
import com.wm.data.ValuesEmulator;
import com.wm.net.HTTPMethod;

public class TNUtils {

	public static String getEventType(TuplePair<IData, String> event) {
		String type = (String) ValuesEmulator.get((IData) event.getFirst(), "evt:Type");
		return type;
	}

	public static void reportAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

	public static void reportDeliveryService(Map<String, Object> attributes, DeliveryService service) {
		if(service != null) {
			reportAttribute(attributes, "DeliveryService-Location", service.getLocation());
			reportAttribute(attributes, "DeliveryService-IFC", service.getIfc());
			reportAttribute(attributes, "DeliveryService-Name", service.getName());
			reportAttribute(attributes, "DeliveryService-SVC", service.getSvc());
		}
	}

	public static void reportHttpMethod(Map<String,Object> attributes, HTTPMethod method) {
		if(method != null) {
			reportAttribute(attributes, "HttpMethod", method.getName());
		}
	}

	public static void reportIWebService_ResponseType(Map<String,Object> attributes, IWebService.ResponseType type) {
		if(type != null) {
			reportAttribute(attributes, "ResponseType", type.name());
		}
	}

	public static void reportAPISearchDocument(Map<String,Object> attributes, APISearchDocument doc) {
		if(doc != null) {
			List<APISearchField> scopedList = doc.getScopeList();
			StringBuffer sb = new StringBuffer();
			int size = scopedList.size();
			sb.append('[');
			for(int i = 0;i < size;i++) {
				APISearchField sf = scopedList.get(i);
				sb.append("{keyword="+sf.getKeyword()+",attributeName="+sf.getAttributeName()+"}");
				if(i < size - 1) {
					sb.append(',');
				}
			}
			sb.append(']');

			reportAttribute(attributes, "APISearchDocument-ScopedList", sb.toString());
			reportAttribute(attributes,"APISearchDocument-Size", doc.getSize());
			reportAttribute(attributes, "APISearchDocument-Condition", doc.getCondition());
			List<Type> typeList = doc.getTypeList();
			size = typeList.size();
			sb = new StringBuffer();
			sb.append('[');
			for(int i=0;i<size;i++) {
				Type type = typeList.get(i);
				sb.append(type);
				if(i < size-1) {
					sb.append(',');
				}
			}
			sb.append(']');
		}
	}

	public static void reportAPIInvocationSearchDocument(Map<String,Object> attributes, APIInvocationSearchDocument searchDoc) {
		if(searchDoc != null) {
			reportAttribute(attributes, "APIInvocationSearchDocument-ApiId", searchDoc.getApiId());
			reportAttribute(attributes, "APIInvocationSearchDocument-ApiName", searchDoc.getApiName());
			reportAttribute(attributes, "APIInvocationSearchDocument-ApplicationId", searchDoc.getApplicationID());
			reportAttribute(attributes, "APIInvocationSearchDocument-FromDate", searchDoc.getFromDate());
			reportAttribute(attributes, "APIInvocationSearchDocument-PartnerId", searchDoc.getPartnerID());
			reportAttribute(attributes, "APIInvocationSearchDocument-ToDate", searchDoc.getToDate());
		}
	}

	public static void reportBizDocEnvelope(Map<String,Object> attributes, BizDocEnvelope doc) {
		if(doc != null) {
			reportAttribute(attributes, "BizDocEnvelope-DocumentId", doc.getDocumentId());
			reportAttribute(attributes, "BizDocEnvelope-GroupId", doc.getGroupId());
			reportAttribute(attributes, "BizDocEnvelope-InternalId", doc.getInternalId());
			reportAttribute(attributes, "BizDocEnvelope-ReceiverId", doc.getReceiverId());
			reportAttribute(attributes, "BizDocEnvelope-SenderId", doc.getSenderId());
		}
	}

	public static void reportBizDocEnvelope(Map<String,Object> attributes, String prefix, BizDocEnvelope doc) {
		if(doc != null) {
			if(prefix == null || prefix.isEmpty()) {
				reportBizDocEnvelope(attributes,doc);
			} else {
				reportAttribute(attributes, prefix + "-BizDocEnvelope-DocumentId", doc.getDocumentId());
				reportAttribute(attributes, prefix + "-BizDocEnvelope-GroupId", doc.getGroupId());
				reportAttribute(attributes, prefix + "-BizDocEnvelope-InternalId", doc.getInternalId());
				reportAttribute(attributes, prefix + "-BizDocEnvelope-ReceiverId", doc.getReceiverId());
				reportAttribute(attributes, prefix + "-BizDocEnvelope-SenderId", doc.getSenderId());
			}
		}
	}

	public static void reportEventQuery(Map<String,Object> attributes, EventQuery query) {
		if(query != null) {
			reportAttribute(attributes, "EventQuery-RelatedPartnerId", query.getRelatedPartnerID());
			reportAttribute(attributes, "EventQuery-EventType", query.getEventType());
			reportAttribute(attributes, "EventQuery-RelatedDocId", query.getRelatedDocID());
			reportAttribute(attributes, "EventQuery-RelatedDocId", query.getRelatedDocTypeID());
			reportAttribute(attributes, "EventQuery-RelatedRuleId", query.getRelatedRuleID());
			reportAttribute(attributes, "EventQuery-RelatedInstanceId", query.getRelatedInstanceID());
			reportAttribute(attributes, "EventQuery-RelatedStepId", query.getRelatedStepID());
		}
	}

	public static void reportSimpleDocQuery(Map<String, Object> attributes, SimpleDocQuery query) {
		if(query != null) {
			reportAttribute(attributes, "SimpleDocQuery-DocumentId", query.getDocumentId());
			reportAttribute(attributes, "SimpleDocQuery-GroupdId", query.getGroupId());
			reportAttribute(attributes, "SimpleDocQuery-InternalId", query.getInternalId());
			reportAttribute(attributes, "SimpleDocQuery-MessageType", query.getMessageType());
			reportAttribute(attributes, "SimpleDocQuery-Receiver", query.getReceiver());
			reportAttribute(attributes, "SimpleDocQuery-Sender", query.getSender());
		}
	}

	public static void reportStringArray(Map<String, Object> attributes,String key, String[] strings) {
		StringBuffer sb = new StringBuffer("[");
		for(int i=0;i<strings.length;i++) {
			sb.append(strings[i]);
			if(i < strings.length-1) {
				sb.append(',');
			}
		}
		sb.append(']');
		reportAttribute(attributes, key, sb.toString());
	}

	public static void reportBizDocContentPart(Map<String,Object> attributes, BizDocContentPart docPart) {
		if(docPart != null) {
			reportAttribute(attributes, "BizDocContentPart-PartName", docPart.getPartName());
			reportAttribute(attributes, "BizDocContentPart-MimeType", docPart.getMimeType());
			reportAttribute(attributes, "BizDocContentPart-StorageType", docPart.getStorageType());
		}
	}
	
	public static void reportDeliveryJobQuery(Map<String,Object> attributes, DeliveryJobQuery query) {
		if(query != null) {
			reportAttribute(attributes, "DeliveryJobQuery-JobId", query.getJobId());
			reportAttribute(attributes, "DeliveryJobQuery-JobType", query.getJobType());
			reportAttribute(attributes, "DeliveryJobQuery-ServiceName", query.getServiceName());
			reportAttribute(attributes, "DeliveryJobQuery-PartnerId", query.getPartnerId());
			reportAttribute(attributes, "DeliveryJobQuery-SenderId", query.getSenderId());
		}
	}
	
	public static void reportGuaranteedJob(Map<String,Object> attributes, GuaranteedJob job) {
		String prefix = job.getClass().getSimpleName();
		if(job != null) {
			reportAttribute(attributes, prefix+"-QueueName", job.getQueueName());
			reportAttribute(attributes, prefix+"-JobId", job.getJobId());
			reportAttribute(attributes, prefix+"-ServerId", job.getServerId());
			reportAttribute(attributes, prefix+"-Classification", job.getClassification());
			reportAttribute(attributes, prefix+"-DeliveryService", job.getDeliveryService());
			reportAttribute(attributes, prefix+"-Service", job.getService());
		}
	}
	
	public static void reportInvite(Map<String,Object> attributes, Invite invite) {
		if(invite != null) {
			reportAttribute(attributes, "Invite-InviteId", invite.getInviteId());
			reportAttribute(attributes, "Invite-CreatedUser", invite.getCreatedUser());
			reportAttribute(attributes, "Invite-EmailFromm", invite.getEmailFrom());
			reportAttribute(attributes, "Invite-InviteName", invite.getInviteName());
			reportAttribute(attributes, "Invite-InviteURL", invite.getInviteURL());
			reportAttribute(attributes, "Invite-Status", invite.getStatus());
		}
	}
	
	public static void reportInviteQuery(Map<String,Object> attributes, InviteQuery query) {
		if(query != null) {
			reportAttribute(attributes, "InviteQuery-CreatedUser", query.getCreatedUser());
			reportAttribute(attributes, "InviteQuery-InviteId", query.getInviteID());
			reportAttribute(attributes, "InviteQuery-InviteName", query.getInviteName());
			reportAttribute(attributes, "InviteQuery-PartnerId", query.getPartnerID());
			reportAttribute(attributes, "InviteQuery-Status", query.getStatus());
			reportAttribute(attributes, "InviteQuery-TemplateId", query.getTemplateID());
		}
	}
	
	public static void reportStringCollection(Map<String,Object> attributes,String key, Collection<String> list) {
		if(list != null) {
			StringBuffer sb = new StringBuffer('{');
			int i = 0;
			int size = list.size();
			for(String s : list) {
				sb.append(s);
				if(i < size -1) {
					sb.append(',');
				}
				i++;
			}
			sb.append('}');
			reportAttribute(attributes, key, sb.toString());
		}
	}
	
}
