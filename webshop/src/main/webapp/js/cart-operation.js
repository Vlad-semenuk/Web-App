 $(document).ready(function(){
          $('.addToCart').click(function(){
             var id = this.getAttribute('data-parameter');
              $.ajax({
                  type: 'POST',
                  data: {id : id},
                  url: 'add',
                  success: function (result) {
                  var returnedData = JSON.parse(result);
                   $('#count').html(returnedData["quantity"]);
                  }
              })
          })
      })