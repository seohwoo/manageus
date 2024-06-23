<script th:inline="javascript">
    $(document).ready(function(){
        const id = $("#userId").val();
        const roomId = $("#roomId").val();

        const websocket = new WebSocket("ws://localhost:8080/ws/chat");

        websocket.onmessage = onMessage;
        websocket.onopen = onOpen;
        websocket.onclose = onClose;

        $("#button-send").on("click", function() {
            send();
        });

        function send(){
            let msg = $("#msg").val();
            let roomId = $("#roomId").val();
            websocket.send(id + ":" + msg);

            $.ajax({
                url: '/companies/send-message',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({ userId: id, message: msg, chatRoomId: roomId }),
                success: function(response) {
                    console.log('메시지 전송 성공');
                },
                error: function(error) {
                    console.log('메시지 전송 오류:', error);
                }
            });

            $("#msg").val('');
        }

        function addMessage(userId, message, isOwnMessage) {
            var containerClass = isOwnMessage ? 'message-container own' : 'message-container';
            var messageClass = isOwnMessage ? 'own-message' : 'other-message';
            var str = "<div class='" + containerClass + "'>";
            str += "<div class='" + messageClass + "'>";
            str += "<b>" + userId + " : " + message + "</b>";
            str += "</div></div>";
            $("#msgArea").append(str);
        }

        function onClose() {
            var str = id + ": 님이 방을 나가셨습니다.";
            websocket.send(str);
        }

        function onOpen() {
            var str = id + ": 님이 입장하셨습니다.";
            websocket.send(str);
        }

        function onMessage(msg) {
            var data = msg.data;
            var arr = data.split(":");
            var userId = arr[0];
            var message = arr[1];

            addMessage(userId, message, userId == id);
        }
    });
</script>