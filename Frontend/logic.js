var email = "david@mail.at";
var reservationArr = [];

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
                    document.getElementById("max-seats").value = maxSeats;
                }
            }
        }
    };
    xhttp.send(null);
}

function postReservation() {
    var dateTime = document.getElementById("datetime").value.toString();
    var seats = document.getElementById("seats").value;
    var email = document.getElementById("email").value;
    var maxSeats = document.getElementById("max-seats").value;
    if (dateTime == "" || seats == "" || email == "" || maxSeats == "") {
        alert("Please enter all necessary data");
    } else {
        if (maxSeats < seats) {
            alert("Sorry, this exceeds our capacity at that time")
            // something weird is happening here..:
            // sometimes the maxSeats "lesser/higher than" seats is doing the opposite of what it  logically should do
            // TODO: what's the reason? ..working atm but check again later
        } else {
            var dataString = "?timeString=" + dateTime + "&seatsString=" + seats + "&email=" + email;
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "http://localhost:8080/reservation" + dataString, true);
            xhttp.send();
            fillReservationTable();
        }
        
    }
}

function getUsersReservations() {
    var getRes = new XMLHttpRequest();
    getRes.open("GET", "http://localhost:8080/reservation/" + email, true)
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
        var row = '<tr id=' + id + '>' + 
        '<td>' + id + '</td>' +
        '<td>' + dtArr[i].date + '</td>' +
        '<td>' + dtArr[i].time + '</td>' +
        '<td>' + reservationArr[i].seats + '</td>' +
        '<td class="icon change" onclick="changeReservation(' + id + ')"><img src="icons/change.png" height=15px></td>' + 
        '<td class="icon delete" onclick="deleteReservation(' + id + ')"><img src="icons/delete.png" height=15px></td>' +
        '</tr>'
        table.innerHTML += row
    }
}

function getDateTimes() {
    dateTimeArr = [];
    reservationArr.forEach(res => {
        var date = res.dateTime.dayOfMonth + "/" + res.dateTime.monthOfYear + "/" + res.dateTime.year;
        var time = res.dateTime.hourOfDay + ":" + res.dateTime.minuteOfHour;
        if (time.length < 5) {
            time = time + "0"
        }
        var dateTime = { date: date, time: time };
        dateTimeArr.push(dateTime);
    });
    return dateTimeArr;
}

function changeReservation(id) {
    var pers = prompt("Please enter your partys size:");
    if (pers == 0) {
        alert('Please press "delete" if you want to cancel')
    } else {
        if (pers != null) {
            
        }
    }
    


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


