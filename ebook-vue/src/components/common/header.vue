<template>
  <div class="headerContainer">
    <div class="navBar">
      <div class="navBar_inner">
        <a href="/">
          <span class="navBar_logo">WEBOOK</span>
        </a>
        <input placeholder="搜索" type="text" class="navBar_input" />
        <div class="avatar_container">
          <a href="javascript:" class="avatar">
            <img src="../../assets/avatar.svg" class="avatar_img" />
          </a>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
.headerContainer {
  position: relative;
  width: 100%;
  padding: 16px 0;
  box-shadow: 0 -8px 21px rgba(0, 25, 104, 0.3);
}

.navBar_inner {
  max-width: 870px;
  height: 100%;
  margin-left: auto;
  margin-right: auto;
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  flex-direction: row;
  -webkit-box-pack: justify;
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
}
.navBar_logo {
  width: 117px;
  height: 32px;
  margin-right: 16px;
}
.avatar_container {
  margin: 0px 14px;
}
.avatar {
  display: block;
}
.avatar_img {
  width: 20px;
  height: 20px;
  margin: 0 auto;
}

.navBar_input {
  -webkit-box-flex: 1;
  flex: 1;
  height: 36px;
  outline: none;
  padding: 0 36px;
  margin: 0 60px;
  border: 0;
  border-radius: 20px;
  background: url(../../assets/search.png) no-repeat;
  background-size: 100%;
  background-repeat: no-repeat;
  background-color: rgba(255, 255, 255, 0.8);
  background-size: 20px 20px;
  background-position: 12px 8px;
}
@media (max-width: 920px) {
  .navBar_input {
    margin-right: 5px;
    margin-left: 5px;
  }
  .navBar_logo {
    display: none;
  }
}
</style>