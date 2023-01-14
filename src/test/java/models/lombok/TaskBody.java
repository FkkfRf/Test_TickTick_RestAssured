package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskBody {
    public List<ListTaskBody.ListAdd> bodyList;

    @Data
    public static class ListTaskBody {
        private ListAdd add;
        private List update;
        private List delete;
        private List addAttachments;
        private List updateAttachments;
        private List deleteAttachments;

        @Data
        public static class ListAdd {
            public List items;
            public List reminders;
            public List exDate;
            public String dueDate;
            public Integer priority;
            public Boolean isAllDay;
            public Integer progress;
            public String assignee;
            public Integer sortOrder;
            public String startDate;
            public Boolean isFloating;
            public Integer status;
            public String projectId;
            public String kind;
            public String createdTime;
            public String modifiedTime;
            public String title;
            public List tags;
            public String timeZone;
            public String content;
            public String id;
        }
    }
}
    /*
        "add": [
        {
        "items": [],
        "reminders": [],
        "exDate": [],
        "dueDate": null,
        "priority": 0,
        "isAllDay": true,
        "progress": 0,
        "assignee": null,
        "sortOrder": -4398046511104,
        "startDate": "2023-01-13T21:00:00.000+0000",
        "isFloating": false,
        "status": 0,
        "projectId": "inbox120897079",
        "kind": null,
        "createdTime": "2023-01-14T16:24:38.000+0000",
        "modifiedTime": "2023-01-14T16:24:38.000+0000",
        "title": "Тестовая задача ",
        "tags": [],
        "timeZone": "Europe/Moscow",
        "content": "",
        "id": "63c2d746a1aa525fe15a0e3f"
        }
        ],
        "update": [],
        "delete": [],
        "addAttachments": [],
        "updateAttachments": [],
        "deleteAttachments": []
        }

     */