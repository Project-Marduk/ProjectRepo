<template id="login">

  <article class="w3-container">

    <div id="login-form " style="width:100%">

      <div class="w3-card-4">
        <div class="w3-container w3-teal">
          <h2>Login</h2>
        </div>

        <form class="w3-container">

          <label class="w3-text-teal"><b>ID</b></label>

          <input class="w3-input w3-border w3-light-grey" type="text">

          <label class="w3-text-teal"><b>Name</b></label>
          <input class="w3-input w3-border w3-light-grey" type="text">

          <button class="w3-btn w3-blue-grey">Register</button>
        </form>
      </div>


    </div>



  </article>

















</template>

<script>
  Vue.component("login", {template: "#login"});

</script>

<style scoped>
    /*.login {*/
    /*  padding: 20px;*/

    /*  display: flex;*/

    /*  align-items: center;*/
    /*}*/
</style>