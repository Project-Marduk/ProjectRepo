<!--that page shows -->
<template id="app-frame">


    <div class="app-frame">
<!-- header -->
      <header>
        <span>Project Application</span>
        <a href="/login">login</a>

        <span v-if="$javalin.state.currentUser">Current user: '{{$javalin.state.currentUser}}'</span>
      </header>
<!--main nav-->
      <div class="w3-bar w3-light-grey">
        <a href="#" class="w3-bar-item w3-button">Home</a>
        <a href="#" class="w3-bar-item w3-button">Link 1</a>
        <div class="w3-dropdown">
          <button class="w3-button">Dropdown</button>
          <div class="w3-dropdown-content w3-card-4">
            <a href="#" class="w3-bar-item w3-button">Link 1</a>
            <a href="#" class="w3-bar-item w3-button">Link 2</a>
            <a href="#" class="w3-bar-item w3-button">Link 3</a>
          </div>
        </div>
      </div>
<!--side nav-->
      <div class="w3-sidebar w3-bar-block" style="width:20%">
        <a href="#" class="w3-bar-item w3-button">Link 1</a>
        <a href="#" class="w3-bar-item w3-button">Link 2</a>
        <a href="#" class="w3-bar-item w3-button">Link 3</a>
      </div>


        <slot></slot>

    </div>
</template>
<script>
    Vue.component("app-frame", {template: "#app-frame"});
</script>
<style>
    html {
        font-family: sans-serif;
    }
    .app-frame > header {
        padding: 20px;
        background: #b6e2ff;
        font-size: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>
