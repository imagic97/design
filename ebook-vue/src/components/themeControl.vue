<template>
  <div class="themeControl">
    <div>
      <el-row>
        <el-col :span="12">
          <div
            class="theme theme-dark"
            @click="setCurrentTheme('dark')"
            :class="{'currentTheme':isCurrentTheme('dark')}"
          ></div>
        </el-col>
        <el-col :span="12">
          <div
            class="theme theme-light"
            @click="setCurrentTheme('light')"
            :class="{'currentTheme':isCurrentTheme('light')}"
          ></div>
        </el-col>
      </el-row>
    </div>
    <div class="fontSize">
      <el-slider
        v-model.lazy="zitiSize"
        :min="10"
        :max="34"
        :show-tooltip="false"
        :step="6"
        show-stops
        :marks="marks"
      ></el-slider>
    </div>
  </div>
</template>

<script>
import { ebookMixin } from "@/util/mixin";
import { addCss, removeCss } from "@/util/utils";
export default {
  mixins: [ebookMixin],
  data() {
    return {
      currentTheme: "light",
      marks: {
        10: {
          style: {
            fontSize: "10px",
            color: "#1989FA"
          },
          label: this.$createElement("strong", "A")
        },
        34: {
          style: {
            fontSize: "34px",
            color: "#1989FA"
          },
          label: this.$createElement("strong", "A")
        }
      }
    };
  },

  computed: {
    zitiSize: {
      get() {
        return this.fontSize;
      },
      set(value) {
        this.setFontSize(value);
      }
    }
  },
  methods: {
    setCurrentTheme(value) {
      if (value === "dark") {
        removeCss(`theme/light.css`);
        addCss(`theme/dark.css`);
      } else {
        removeCss(`theme/dark.css`);
        addCss(`theme/light.css`);
      }
      this.currentTheme = value;
    },
    isCurrentTheme(value) {
      return this.currentTheme === value;
    }
  }
};
</script>

<style scoped>
.themeControl {
  position: absolute;
  bottom: 0;
  width: 100%;
  overflow: auto;
  box-shadow: 0 -8px 21px rgba(0, 25, 104, 0.3);
  /* background-color: #fefefe; */
  padding: 0 0 54px 0;
}

.fontSize {
  margin: 0 auto;
  width: 82%;
}
.theme {
  margin: 16px 60px;
  height: 30px;
  border-radius: 15px;
}
.currentTheme {
  border: 2px solid #1b88ee;
}

.theme-dark {
  background: rgb(56, 56, 56);
}
.theme-light {
  background: #f5f5dc;
}
</style>
