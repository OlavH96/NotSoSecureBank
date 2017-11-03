$.ajax({
    url: 'webresources/user/nissen@ikkesaasikkerbanken.no',
    type: 'GET',
    dataType: 'json',
    success: function(data) {
      console.log(data)
    },
    error: function() {
        console.log("error")
    }
});