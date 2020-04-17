jQuery(document).ready(function(){
	// FromDate
	var date = new Date();
	date.setMonth(date.getMonth());
	var months = 12;
	var monthNames = ["January", "February", "March", "April", "May", "June",
	  "July", "August", "September", "October", "November", "December"
	];
	var select = document.getElementById('fromMonth');
	var html = '';
	for (var i = 0; i < months; i++) {
	  var m = date.getMonth();
	  html += '<option value="' + monthNames[m] + '">' + monthNames[m] + '</option>'
	  date.setMonth(date.getMonth() + 1);
	}
	select.innerHTML = html;
	
	// ToDate
	var date = new Date();
	date.setMonth(date.getMonth() + 3);
	var months = 12;
	var monthNames = ["January", "February", "March", "April", "May", "June",
	  "July", "August", "September", "October", "November", "December"
	];
	var select = document.getElementById('toMonth');
	var html = '';
	for (var i = 0; i < months; i++) {
	  var m = date.getMonth();
	  html += '<option value="' + monthNames[m] + '">' + monthNames[m] + '</option>'
	  date.setMonth(date.getMonth() + 1);
	}
	select.innerHTML = html;
	
	$('#fromYear').each(function() {

		  var year = (new Date()).getFullYear();
		  var current = year;
		  year -= 3;
		  for (var i = 0; i < 6; i++) {
		    if ((year+i) == current)
		      $(this).append('<option selected value="' + (year + i) + '">' + (year + i) + '</option>');
		    else
		      $(this).append('<option value="' + (year + i) + '">' + (year + i) + '</option>');
		  }

		});
	$('#toYear').each(function() {

		  var year = (new Date()).getFullYear();
		  var current = year;
		  year -= 3;
		  for (var i = 0; i < 6; i++) {
		    if ((year+i) == current)
		      $(this).append('<option selected value="' + (year + i) + '">' + (year + i) + '</option>');
		    else
		      $(this).append('<option value="' + (year + i) + '">' + (year + i) + '</option>');
		  }

		});
	
	
	cal_days_labels = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
	cal_months_labels = ['January', 'February', 'March', 'April',
	                 'May', 'June', 'July', 'August', 'September',
	                 'October', 'November', 'December'];
	// these are the days of the week for each month, in order
	cal_days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

	// this is the current date
	cal_current_date = new Date();

	function Calendar(month, year) {
	    this.month = (isNaN(month) || month == null) ? cal_current_date.getMonth() : month;
	    this.year = (isNaN(year) || year == null) ? cal_current_date.getFullYear() : year;
	    this.html = '';
	}


	Calendar.prototype.generateHTML = function () {

	    // get first day of month
	    var firstDay = new Date((new Date().getFullYear(), 0, 1));
	    // var firstDay = new Date(this.year, this.month, 1);
	    var startingDay = firstDay.getDay();

	    // find number of days in month
	    var monthLength = cal_days_in_month[this.month];

	    // compensate for leap year
	    if (this.month == 1) { // February only!
	        if ((this.year % 4 == 0 && this.year % 100 != 0) || this.year % 400 == 0) {
	            monthLength = 29;
	        }
	    }

	    // do the header
	    var html = ""
	    for(var cal=0; cal<3; cal++){
	     var curr = new Date(this.year, (this.month+cal), 1);
	     var startingDay = curr.getDay();
	     console.log(startingDay);
	      var monthName = cal_months_labels[this.month+cal];
	      var monthLength = cal_days_in_month[this.month+cal];
	      html += '<table class="calendar-table">';
	      html += '<tr><th colspan="7">';
	      html += monthName + "&nbsp;" + this.year;
	      html += '</th></tr>';
	      html += '<tr class="calendar-header">';
	      for (var i = 0; i <= 6; i++) {
	          html += '<td class="calendar-header-day">';
	          html += cal_days_labels[i];
	          html += '</td>';
	      }
	      html += '</tr><tr>';

	      // fill in the days
	      var day = 1;
	      // this loop is for is weeks (rows)
	      for (var i = 0; i < 9; i++) {
	          // this loop is for weekdays (cells)
	          for (var j = 0; j <= 6; j++) {
	              html += '<td class="calendar-day">';
	              if (day <= monthLength && (i > 0 || j >= startingDay)) {
	                  html += day;
	                  day++;
	              }
	              html += '</td>';
	          }
	          // stop making rows if we've run out of days
	          if (day > monthLength) {
	              break;
	          } else {
	              html += '</tr><tr>';
	          }
	      }
	      html += '</tr></table>';
	    
	    }// end of calendar loop

	    this.html = html;
	    
	}

	Calendar.prototype.getHTML = function () {
	    return this.html;
	}

	var cal = new Calendar();
	cal.generateHTML();
	document.getElementById("allCalendar").innerHTML = cal.getHTML()
	
});