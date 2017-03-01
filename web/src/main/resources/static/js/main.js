var userName = "TestPekka";
var room = "general";

function post(url, data) {
    return $.ajax({
        type: 'POST',
        url: url,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(data)
    })
}

function appendMessage(message) {
    var fromNow = moment(message.time).format('HH:mm:ss');
    //noinspection JSAnnotator
    var $message = $(`<li class="clearfix"><div class="message-data ${message.sender == userName ? 'align-left' : 'align-right'}"><span class="message-data-name">${message.sender}</span> <span class="message-data-time">${fromNow}</span></div><div class="message ${message.sender == userName ? 'my-message' : 'other-message float-right'}">${message.message}</div></li>`);
    var $messages = $('#messages');
    $messages.append($message);
    $messages.scrollTop($messages.prop("scrollHeight"));
}

function getUserName() {
    $.get('/chat/username').done(function (name) {
        userName = name;
        $("#usernameHolder").html(name + "@" + room);
    });
}

function getPreviousMessages() {
    $('#messages').empty();
    $.get('/chat/' + room).done(messages => messages.forEach(appendMessage));
}

function displayRooms(room) {
    var $chatroomSelect = $('#chatroom');
    var $chatroom = $('<option>' + room + '</option>');
    $chatroomSelect.append($chatroom)
}

function getRooms() {
    $.get('/chat/room').done(rooms => rooms.forEach(displayRooms));
}

function sendMessage() {
    var $messageInput = $('#messageInput');
    var message = {message: $messageInput.val(), sender: userName, room: room};
    $messageInput.val('');
    post('/chat', message);
}

function onNewMessage(result) {
    var message = JSON.parse(result.body);
    appendMessage(message);
}

function connectWebSocket() {
    var socket = new SockJS('/chatWS');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages/' + room, onNewMessage);
    });
}

function changeRoom() {
    room = $('#chatroom option:selected').val();
    connectWebSocket();
    $("#usernameHolder").html(userName + "@" + room);
    getPreviousMessages();
    $message.focus();
}

getRooms();
getPreviousMessages();
connectWebSocket();
getUserName();
