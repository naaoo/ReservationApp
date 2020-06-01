var reservationArr = [];
var user = {};

// TODO: still a lot..
// Implement Token
// check if enough seats left when reservation is changed/ made (server-side)
// don't allow inputs higher than maxSeats;


function logIn() {
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var xhttp = new XMLHttpRequest();
    var dataString = "?email=" + email + "&password=" + password;
    xhttp.open("GET", "http://localhost:8080/login" + dataString, true);
    xhttp.onreadystatechange = function () {
        if (this.readyState !== 4 && this.status == 200) {
            if (this.responseText !== null && this.responseText.length > 0) {
                user = JSON.parse(this.responseText);
                localStorage.setItem("userKey", this.responseText);
                window.location.href = "reservation.html";
            } else {
                alert("Wrong email or password");
                // this is executed even when "if" condition is met... Why?
            }

        }
    }
    xhttp.send();
}

function getMaxSeats() {
    var dateTime = document.getElementById("datetime").value.toString();
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "http://localhost:8080/maxSeats?timeString=" + dateTime, true)
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText !== null && this.responseText.length > 0) {
                var maxSeats = JSON.parse(this.responseText);
                if (maxSeats == 0) {
                    alert("Sorry, at that time we don't have any open seats");
                }
                if (maxSeats !== null) {
                    var divSeats = document.getElementById("seats-div");
                    var seats = '<label for="seats">Your seats: </label>' +
                        '<input type="number" id="seats" name="seats" min="1" max="' + maxSeats + '">';
                    divSeats.innerHTML = seats;
                    var divSubmit = document.getElementById("submit-div");
                    var button = '<button type="button" onclick="postReservation()">submit</button>';
                    divSubmit.innerHTML = button;
                }
            }
        }
    };
    xhttp.send(null);
}

function postReservation() {
    var dateTime = document.getElementById("datetime").value.toString();
    var seats = document.getElementById("seats").value;
    if (dateTime == "" || seats == "") {
        alert("Please enter all necessary data");
    } else {
        var dataString = "?timeString=" + dateTime + "&seatsString=" + seats + "&email=" + user.email;
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "http://localhost:8080/reservation" + dataString, true);
        xhttp.send();
        getUsersReservations();
        var divSeats = document.getElementById("seats-div");
        divSeats.innerHTML = "";
        var divSubmit = document.getElementById("submit-div");
        divSubmit.innerHTML = "";

    }
}

function fetchUser() {
    user = JSON.parse(localStorage.getItem("userKey"));
}

function getUsersReservations() {
    var getRes = new XMLHttpRequest();
    getRes.open("GET", "http://localhost:8080/reservation/" + user.email, true)
    getRes.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            reservationArr = JSON.parse(this.responseText);
            fillReservationTable();
        }
    }
    getRes.send();
}

function fillReservationTable() {
    var table = document.getElementById("reservation-table");
    table.innerHTML = "";
    var dtArr = getDateTimes();
    for (var i = 0; i < reservationArr.length; i++) {
        var id = reservationArr[i].id;
        var row = '<tr>' +
            '<td>' + dtArr[i].date + '</td>' +
            '<td>' + dtArr[i].time + '</td>' +
            '<td>' + reservationArr[i].seats + '</td>' +
            '<td class="icon change" title="change persons" onclick="putReservation(' + id + ')"><img src="icons/change.png" height=15px></td>' +
            '<td class="icon delete" title="delete" onclick="deleteReservation(' + id + ')"><img src="icons/delete.png" height=15px></td>' +
            '</tr>'
        table.innerHTML += row;
    }
}

function getDateTimes() {
    dateTimeArr = [];
    reservationArr.forEach(res => {
        var date = res.dateTime.dayOfMonth + "/" + res.dateTime.monthOfYear + "/" + res.dateTime.year;
        var time = res.dateTime.hourOfDay + ":" + res.dateTime.minuteOfHour;
        if (res.dateTime.minuteOfHour < 10) {
            time = time.slice(0, 3) + "0" + time.slice(3)
        }
        var dateTime = { date: date, time: time };
        dateTimeArr.push(dateTime);
    });
    return dateTimeArr;
}

function putReservation(id) {
    var pers = prompt("Please enter your partys size:");
    if (pers == 0) {
        alert('Please press "delete" if you want to cancel')
    } else {
        if (pers != null) {
            var putRes = new XMLHttpRequest();
            var dataString = "?id=" + id + "&seats=" + pers;
            putRes.open("PUT", "http://localhost:8080/reservation" + dataString, true);
            putRes.send();
            changeReservation(id, pers);
            fillReservationTable();
        }
    }
}

function changeReservation(id, pers) {
    var changeIndex;
    for (var i = 0; i < reservationArr.length; i++) {
        if (reservationArr[i].id == id) {
            changeIndex = i;
        }
    }
    reservationArr[changeIndex].seats = pers;
}

function deleteReservation(id) {
    var del = confirm("Please confirm cancellation");
    if (del) {
        var delRes = new XMLHttpRequest();
        delRes.open("DELETE", "http://localhost:8080/reservation/" + id, true)
        delRes.send();
    }
    removeReservation(id);
    fillReservationTable();
}

function removeReservation(id) {
    var removeIndex;
    for (var i = 0; i < reservationArr.length; i++) {
        if (reservationArr[i].id == id) {
            removeIndex = i;
        }
    }
    reservationArr.splice(removeIndex, 1);
}

function logOut() {
    console.log("Ok");
    user = null;
    localStorage.setItem("userKey", null);
    window.location.href="index.html";
}


