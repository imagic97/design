<template>
  <div class="themeControl" v-show="menuVisible && menuShow === 2">
    <header class="component_title">主题</header>
    <div class="themeContainer">
      <div
        class="theme theme-dark"
        @click="setCurrentTheme('dark')"
        :class="{ currentTheme: isCurrentTheme('dark') }"
      >
        <span class="iconfont icon-dark" style=""></span>
      </div>
      <div
        class="theme theme-light"
        @click="setCurrentTheme('light')"
        :class="{ currentTheme: isCurrentTheme('light') }"
      >
        <span class="iconfont icon-light"></span>
      </div>
    </div>
    <div class="fontSize">
      <input
        class="fontSizeValue"
        v-model="zitiSize"
        type="range"
        max="34"
        min="10"
        step="6"
      />
    </div>
  </div>
</template>

<script>
import { ebookMixin } from "@/util/mixin";
import lS from "@/util/localStorage";

export default {
  mixins: [ebookMixin],
  data() {
    return {
      currentTheme: "",
    };
  },

  mounted() {
    let value = lS.get("theme");
    if (value != null) {
      this.setCurrentTheme(value);
    } else {
      value = "light";
      this.setCurrentTheme(value);
    }
    this.currentTheme = value;
  },

  computed: {
    zitiSize: {
      get() {
        return this.fontSize;
      },
      set(value) {
        this.setFontSize(value);
      },
    },
  },
  methods: {
    setCurrentTheme(value) {
      if (value === this.currentTheme) return;
      let removeDom = document.getElementById("themeStyle");
      if (removeDom != null) {
        removeDom.parentNode.removeChild(removeDom);
      }
      if (value === "dark") {
        this.CreateStyle(this.THEME_DARK, "themeStyle");
      } else {
        this.CreateStyle(this.THEME_LIGHT, "themeStyle");
      }
      this.currentTheme = value;
      lS.set("theme", value);
    },
    isCurrentTheme(value) {
      return this.currentTheme === value;
    },
  },
};
</script>

<style scoped>
.themeControl {
  position: absolute;
  bottom: 64px;
  width: 100%;
  border-radius: 16px 16px 0 0;
  box-shadow: 0 -1px 3px rgba(0, 25, 104, 0.3);
  padding: 0 0 54px 0;
}
.themeContainer {
  display: flex;
}
.component_title {
  font-size: 22px;
  margin: 12px 0;
  text-align: center;
}
.fontSize {
  margin: 0 auto;
  padding: 16px 0 0 0;
  width: 86%;
}
.theme {
  flex: 1;
  margin: 16px 60px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
}
.iconfont {
  color: #1b88ee;
}

@media screen and (max-width: 500px) {
  .theme {
    margin: 16px 23px;
    height: 30px;
    border-radius: 15px;
  }
}
.currentTheme {
  border: 2px solid #1b88ee;
}

.theme-dark {
  background: rgb(56, 56, 56);
}
.theme-light {
  background: #f1f1f1;
}

.fontSizeValue {
  width: 100%;
  -webkit-appearance: none;
  height: 8px;
  background: -webkit-linear-gradient(#1b88ee, #1b88ee) no-repeat, #1b88ee;
  background-size: 100%;
  border-radius: 4px;
}

.fontSizeValue:focus {
  outline: none;
}
.fontSizeValue::-webkit-slider-thumb {
  -webkit-appearance: none;
  height: 24px;
  width: 24px;
  border-radius: 50%;
  background: #bbbbe0;
  border: none;
}
</style>
